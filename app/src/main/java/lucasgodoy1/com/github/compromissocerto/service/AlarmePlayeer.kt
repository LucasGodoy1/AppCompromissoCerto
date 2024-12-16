package lucasgodoy1.com.github.compromissocerto.service

import android.content.Context
import android.media.RingtoneManager
import android.os.Handler
import android.os.Looper

object AlarmePlayer {
    private var ringtone: android.media.Ringtone? = null
    private var isTocando = false


    fun inicializar(context: Context) {
        if (ringtone == null) {
            val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
                ?: RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            ringtone = RingtoneManager.getRingtone(context, alarmUri)
        }
    }

    fun iniciarSomDoAlarme() {
        tocar()
        pararSomEmCincoMin()
    }

    private fun tocar() {
        ringtone?.let {
            if (!it.isPlaying && !isTocando) {
                it.play()
                isTocando = true
            }
        }
    }

    fun pararSomDeAlarme() {
        ringtone?.let {
            if (it.isPlaying) {
                it.stop()
                isTocando = false
            }
        }
    }

    private fun pararSomEmCincoMin() {
        ringtone?.let {
            if (it.isPlaying) {
                Handler(Looper.getMainLooper()).postDelayed({
                    it.stop()
                    isTocando = false
                }, 300000) // 300.000 ms = 5 minutos
            }
        }
    }
}
