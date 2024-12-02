package lucasgodoy1.com.github.compromissocerto.controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import lucasgodoy1.com.github.compromissocerto.ui.AlarmeActivity
import lucasgodoy1.com.github.compromissocerto.util.TAG

class AlarmeReceiverController : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "Seu Alarme foi acionado")
        val alarmeID = intent.getIntExtra("alarmeId", 0)

        val intentTela = Intent(context, AlarmeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK

            if (alarmeID != 0){
                putExtra("alarmeID", alarmeID)
            }else{
                Log.e(TAG, "ERRO!: INTENCAO DO ALARME ID ESTA NULA")
            }
        }
        context.startActivity(intentTela)
    }
}