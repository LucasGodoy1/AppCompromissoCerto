package lucasgodoy1.com.github.compromissocerto.view

import android.widget.TextView
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.ui.AlarmeActivity

class AlarmeActivityComponente (val alarmeActivity : AlarmeActivity) {
    val txtViewTitulo : TextView = alarmeActivity.findViewById(R.id.idTxtAlarme)
    val txtViewDataEHora : TextView = alarmeActivity.findViewById(R.id.idTxtAlarmeDataeHR)
}