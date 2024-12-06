package lucasgodoy1.com.github.compromissocerto.config

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import lucasgodoy1.com.github.compromissocerto.controller.AlarmeReceiverController
import lucasgodoy1.com.github.compromissocerto.util.TAG
import lucasgodoy1.com.github.compromissocerto.util.permissoesDoApp


class AlarmeConfig(private var context: Context) {
    val gerenciadorDeAlarmes = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    lateinit var intencaoDeAlarme: Intent
    lateinit var intencaoPendente: PendingIntent




    @RequiresApi(Build.VERSION_CODES.S)
    private fun criarIntencao(alarmeID: Int) {
        if (gerenciadorDeAlarmes.canScheduleExactAlarms()) {
            intencaoDeAlarme = Intent(context, AlarmeReceiverController::class.java).apply {
                putExtra("alarmeId", alarmeID)
            }

            intencaoPendente = PendingIntent.getBroadcast(
                context,
                alarmeID,
                intencaoDeAlarme,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            Log.w(TAG, "Intencao criada")

        } else {
            permissoesDoApp(context)
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun agendarAlarme(horarioEmMilissegundos: Long, alarmeID: Int) {
        criarIntencao(alarmeID)
        gerenciadorDeAlarmes.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            horarioEmMilissegundos,
            intencaoPendente
        )
        Log.w(TAG, "Alarme agendado")

    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun excluirAlarme(alarmeID: Int) {
        val intencaoDeAlarme = Intent(context, AlarmeReceiverController::class.java).apply {
            putExtra("alarmeId", alarmeID)
        }

        val intencaoPendente = PendingIntent.getBroadcast(
            context,
            alarmeID,
            intencaoDeAlarme,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        gerenciadorDeAlarmes.cancel(intencaoPendente)
        intencaoPendente.cancel()

        Log.i(TAG, "Alarme com ID $alarmeID excluido")
    }



}