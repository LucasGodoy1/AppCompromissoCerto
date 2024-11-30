package lucasgodoy1.com.github.compromissocerto.controller

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import lucasgodoy1.com.github.compromissocerto.util.TAG

class AlarmeReceiverController: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG,"Seu Alarme foi ancionado")
    }
}