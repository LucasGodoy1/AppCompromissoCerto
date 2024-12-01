package lucasgodoy1.com.github.compromissocerto.ui

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

class SplashActivity : AppCompatActivity() {
    val tempoDeEspera = 1000L

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)

        Log.w(TAG, "SplashActivity Iniciada")
        trocarTela()
    }


    private fun trocarTela() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, UsuarioActivity::class.java))
            finish()
        }, tempoDeEspera)
    }


}