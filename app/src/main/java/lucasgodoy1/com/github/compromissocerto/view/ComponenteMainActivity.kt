package lucasgodoy1.com.github.compromissocerto.view

import android.widget.CalendarView
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import lucasgodoy1.com.github.compromissocerto.ui.MainActivity
import lucasgodoy1.com.github.compromissocerto.R

class ComponenteMainActivity (mainActivity: MainActivity){
    var caixaDigiteSeuCompromisso : EditText = mainActivity.findViewById(R.id.idCaixaDigit)
    var calendario : CalendarView = mainActivity.findViewById(R.id.idCalendario)
    var hora : EditText = mainActivity.findViewById(R.id.idHora)
    var btnSalvar : AppCompatButton = mainActivity.findViewById(R.id.idBtnSalvar)


}