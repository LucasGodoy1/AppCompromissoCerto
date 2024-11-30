package lucasgodoy1.com.github.compromissocerto.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.controller.UsuarioController
import lucasgodoy1.com.github.compromissocerto.util.TAG

class UsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_usuario)
        Log.w(TAG,"UsuarioActivity Iniciada")
        val componenteUsuario = UsuarioController(this)
        componenteUsuario.inicializar()
    }
}