package lucasgodoy1.com.github.compromissocerto.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.controller.AlarmeActivityController

class AlarmeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alarme)

        val alarmeID = intent.getIntExtra("alarmeID", 0)
        val alarmeActivityController = AlarmeActivityController(this, alarmeID.toString())
        alarmeActivityController.inicializar()


    }


}