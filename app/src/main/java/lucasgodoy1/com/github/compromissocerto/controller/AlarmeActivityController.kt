package lucasgodoy1.com.github.compromissocerto.controller

import android.view.View
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.service.AlarmePlayer
import lucasgodoy1.com.github.compromissocerto.ui.AlarmeActivity
import lucasgodoy1.com.github.compromissocerto.view.AlarmeActivityComponente

class AlarmeActivityController(val alarmeActivity : AlarmeActivity, val AlarmeID: String) {
    private val alarmeComponente = AlarmeActivityComponente(alarmeActivity)
    private val data = CompromissoDataBase(alarmeActivity)
    private val alarmePlayer = AlarmePlayer(alarmeActivity)

    fun inicializar(){
        exibirMsg()
        alarmePlayer.iniciarSomDoAlarme()
        botaoParar()
    }

    fun exibirMsg(){
        val usuario = data.buscarDados(AlarmeID)
        if (usuario != null){
            alarmeComponente.txtViewTitulo.setText(usuario.compromisso)
            alarmeComponente.txtViewDataEHora.setText("Hora: ${usuario.hora}")
        }
    }

    fun botaoParar(){
        alarmeComponente.btnParar.setOnClickListener(View.OnClickListener {
            alarmePlayer.pararSomDeAlarme()
            alarmeActivity.finish()
        })
    }





}