package lucasgodoy1.com.github.compromissocerto.controller

import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.ui.AlarmeActivity
import lucasgodoy1.com.github.compromissocerto.view.AlarmeActivityComponente

class AlarmeActivityController(val alarmeActivity : AlarmeActivity, val AlarmeID: String) {
    private val alarmeComponente = AlarmeActivityComponente(alarmeActivity)
    private val data = CompromissoDataBase(alarmeActivity)

    fun inicializar(){
        exibirMsg()
    }

    fun exibirMsg(){
        val usuario = data.buscarDados(AlarmeID)
        if (usuario != null){
            alarmeComponente.txtViewTitulo.setText(usuario.compromisso)
            alarmeComponente.txtViewDataEHora.setText("Hora: ${usuario.hora}")
        }
    }


}