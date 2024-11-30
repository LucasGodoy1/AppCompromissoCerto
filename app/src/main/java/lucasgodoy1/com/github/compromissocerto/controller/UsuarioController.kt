package lucasgodoy1.com.github.compromissocerto.controller

import android.app.Activity
import android.view.View
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.ui.UsuarioActivity
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela
import lucasgodoy1.com.github.compromissocerto.view.UsuarioActivityComponente

class UsuarioController (var usuarioActivity: UsuarioActivity){
    var componenteUsuario = UsuarioActivityComponente(usuarioActivity)

    fun inicializar(){
        btnAdcNovoCompromisso(AdicionarActivity::class.java)
        btnMeusCompromissos(CompromissosActivity ::class.java)
    }


    private fun <R : Activity> btnAdcNovoCompromisso(destino: Class<R>) {
        componenteUsuario.btnAdicionar.setOnClickListener(View.OnClickListener {
            trocarDeTela(usuarioActivity, destino)

        })
    }



    private fun <R : Activity>  btnMeusCompromissos(destino: Class<R>){
        componenteUsuario.btnMeusCompromissos.setOnClickListener(View.OnClickListener {
            trocarDeTela(usuarioActivity, destino)
        })
    }


}