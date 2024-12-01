package lucasgodoy1.com.github.compromissocerto.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.controller.EditarActivityController
import lucasgodoy1.com.github.compromissocerto.util.TAG

class EditarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editar)
        Log.w(TAG, "EditarActivity Iniciada")

        val editarActivityController = EditarActivityController(this)

        editarActivityController.inicializar()
    }
}