package lucasgodoy1.com.github.compromissocerto.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.controller.UsuarioController

class UsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_usuario)
        val componenteUsuario = UsuarioController(this)
        componenteUsuario.btnAdcNovoCompromisso(AdicionarActivity::class.java)
        componenteUsuario.btnMeusCompromissos(CompromissosActivity ::class.java)
    }
}