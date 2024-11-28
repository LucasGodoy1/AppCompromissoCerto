package lucasgodoy1.com.github.compromissocerto.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.controller.EditarActivityController

class EditarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar)

        val editarActivityController = EditarActivityController(this)

        editarActivityController.seuCompromissoAtualEdit()
        editarActivityController.botaoSalvarTelaEditar()
    }
}