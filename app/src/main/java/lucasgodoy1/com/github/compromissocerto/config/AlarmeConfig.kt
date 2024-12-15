package lucasgodoy1.com.github.compromissocerto.config

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import lucasgodoy1.com.github.compromissocerto.controller.AlarmeReceiverController
import lucasgodoy1.com.github.compromissocerto.util.verificarPermissoes

class AlarmeConfig(private var context: Context) {
    private val gerenciadorDeAlarmes = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private lateinit var intencaoDeAlarme: Intent
    private lateinit var intencaoPendente: PendingIntent
    private val TAG_ALARME_CONFIG = "LOG_ALARME_CONFIG"


    // Criação da intenção e PendingIntent
    private fun criarIntencao(alarmeID: Int) {
        intencaoDeAlarme = Intent(context, AlarmeReceiverController::class.java).apply {
            putExtra("alarmeId", alarmeID)
        }

        intencaoPendente = PendingIntent.getBroadcast(
            context,
            alarmeID,
            intencaoDeAlarme,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        Log.w(TAG_ALARME_CONFIG, "Intenção criada")
    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun agendarAlarme(horarioEmMilissegundos: Long, alarmeID: Int) {
        if (!verificarPermissoes(context)) {
            Log.e(TAG_ALARME_CONFIG, "Permissões necessárias não concedidas. Alarme não agendado.")
            return
        }

        criarIntencao(alarmeID)

        if (gerenciadorDeAlarmes.canScheduleExactAlarms()){
            gerenciadorDeAlarmes.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                horarioEmMilissegundos,
                intencaoPendente
            )
        }
        Log.w(TAG_ALARME_CONFIG, "Alarme agendado com ID $alarmeID")
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

        Log.i(TAG_ALARME_CONFIG, "Alarme com ID $alarmeID excluído")
    }
}
