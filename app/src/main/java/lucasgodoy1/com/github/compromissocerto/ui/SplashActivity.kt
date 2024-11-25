package lucasgodoy1.com.github.compromissocerto.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import lucasgodoy1.com.github.compromissocerto.R

class SplashActivity : AppCompatActivity() {
    val tempoDeEspera = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        trocarTela()
    }



    private fun trocarTela() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, UsuarioActivity::class.java))
            finish()
        }, tempoDeEspera)
    }


}