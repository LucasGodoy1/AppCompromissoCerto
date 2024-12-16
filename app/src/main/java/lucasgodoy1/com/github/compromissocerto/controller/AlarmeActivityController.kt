package lucasgodoy1.com.github.compromissocerto.controller

import android.content.Context
import android.view.View
import lucasgodoy1.com.github.compromissocerto.datasource.AppDataBase
import lucasgodoy1.com.github.compromissocerto.service.AlarmePlayer
import lucasgodoy1.com.github.compromissocerto.ui.AlarmeActivity
import lucasgodoy1.com.github.compromissocerto.view.AlarmeActivityComponente

class AlarmeActivityController(val alarmeActivity : AlarmeActivity, val AlarmeID: String) {
    private val alarmeComponente = AlarmeActivityComponente(alarmeActivity)
    private val data = AppDataBase(alarmeActivity)
    val sharedPreferences = alarmeActivity.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)



    fun inicializar(){
        AlarmePlayer.inicializar(alarmeActivity)
        exibirMsg()
        AlarmePlayer.iniciarSomDoAlarme()
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
            AlarmePlayer.pararSomDeAlarme()
            alarmeActivity.finish()
            sharedPreferences.edit().clear().apply()
        })
    }


}