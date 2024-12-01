package lucasgodoy1.com.github.compromissocerto.view

import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.ui.EditarActivity

class EditarActivityComponente(val editarActivity: EditarActivity) {

    var editCompromisso: EditText = editarActivity.findViewById(R.id.idEditandoCompromisso)
    var editCalendario: CalendarView = editarActivity.findViewById(R.id.idCalendario)
    var editHora: EditText = editarActivity.findViewById(R.id.idEditTime)
    val btnSalvar: AppCompatButton = editarActivity.findViewById(R.id.idEditBtnSalvar)

}