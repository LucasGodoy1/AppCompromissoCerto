package lucasgodoy1.com.github.compromissocerto.ui

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.util.TAG
import lucasgodoy1.com.github.compromissocerto.util.solicitarAbrirNovaJanela
import lucasgodoy1.com.github.compromissocerto.util.verificarMostrarTelaDeBloqueio

class SplashActivity : AppCompatActivity() {
    val tempoDeEspera = 1000L


    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Log.w(TAG, "SplashActivity Iniciada")
        verificarAlarmeAtivo()
    }



    private fun verificarAlarmeAtivo() {
        val sharedPreferences = getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
        val alarmeID = sharedPreferences.getInt("alarme_ativo_id", 0)

        if (alarmeID != 0) {
            Log.i(TAG, "Alarme ativo detectado. Redirecionando para AlarmeActivity.")
            val intent = Intent(this, AlarmeActivity::class.java).apply {
                putExtra("alarmeID", alarmeID)
            }
            startActivity(intent)
            finish()

        } else {
            trocarTela()
        }
    }

    private fun trocarTela() {
        Handler(Looper.getMainLooper()).postDelayed({
            verificarMostrarTelaDeBloqueio(this)
            solicitarAbrirNovaJanela(this)
            startActivity(Intent(this, UsuarioActivity::class.java))
            finish()
        }, tempoDeEspera)
    }
}
