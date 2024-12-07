package lucasgodoy1.com.github.compromissocerto.service

import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Handler
import android.os.Looper

class AlarmePlayer(val context: Context) {
    val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    private val ringtone = RingtoneManager.getRingtone(context, alarmUri)



    fun iniciarSomDoAlarme(){
        ringtone.play()
        PararSomEmCincoMin()
    }

    fun pararSomDeAlarme(){
        if (ringtone.isPlaying){
            ringtone.stop()
        }
    }

    fun PararSomEmCincoMin(){
        if (ringtone.isPlaying){

            Handler(Looper.getMainLooper()).postDelayed({
                ringtone.stop()
            }, 300000)
        }
    }

}