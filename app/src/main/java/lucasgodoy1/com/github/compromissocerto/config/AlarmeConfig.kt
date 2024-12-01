package lucasgodoy1.com.github.compromissocerto.config

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import lucasgodoy1.com.github.compromissocerto.controller.AlarmeReceiverController
import lucasgodoy1.com.github.compromissocerto.util.TAG


class AlarmeConfig(private var context: Context) {
    val gerenciadorDeAlarmes = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    lateinit var intencaoDeAlarme: Intent
    lateinit var intencaoPendente: PendingIntent

    fun permitirAlarme() {
        Toast.makeText(
            context, "Permissão necessária para configurar alarmes. Ative nas configurações.",
            Toast.LENGTH_LONG
        ).show()

        val intentParaConfiguracoes =
            Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                data = Uri.parse("package:${context.packageName}")
            }
        context.startActivity(intentParaConfiguracoes)
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun criarIntencao() {
        if (gerenciadorDeAlarmes.canScheduleExactAlarms()) {
            intencaoDeAlarme = Intent(context, AlarmeReceiverController::class.java)

            intencaoPendente = PendingIntent.getBroadcast(
                context,
                0,
                intencaoDeAlarme,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            Log.w(TAG,"Intencao criada")

        } else {
            permitirAlarme()
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun agendarAlarme(horarioEmMilissegundos: Long) {
        criarIntencao()
        gerenciadorDeAlarmes.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            horarioEmMilissegundos,
            intencaoPendente
        )
        Log.w(TAG,"Alarme agendado")

    }
}