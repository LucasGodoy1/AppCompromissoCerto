package lucasgodoy1.com.github.compromissocerto.view

import androidx.appcompat.widget.AppCompatButton
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.ui.UsuarioActivity

class ComponenteUsuarioActivity (usuarioActivity: UsuarioActivity){
    var btnAdicionar : AppCompatButton = usuarioActivity.findViewById(R.id.idBtnAdc)
    var btnMeusCompromissos : AppCompatButton = usuarioActivity.findViewById(R.id.idBtnMeusCompromissos)

}