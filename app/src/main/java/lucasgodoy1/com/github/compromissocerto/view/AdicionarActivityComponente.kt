package lucasgodoy1.com.github.compromissocerto.view

import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.R

class AdicionarActivityComponente(adicionarActivity: AdicionarActivity) {
    var caixaDigiteSeuCompromisso: EditText = adicionarActivity.findViewById(R.id.idCaixaDigit)
    var calendario: CalendarView = adicionarActivity.findViewById(R.id.idCalendario)
    var hora: EditText = adicionarActivity.findViewById(R.id.idHora)
    var btnSalvar: AppCompatButton = adicionarActivity.findViewById(R.id.idBtnSalvar)


}