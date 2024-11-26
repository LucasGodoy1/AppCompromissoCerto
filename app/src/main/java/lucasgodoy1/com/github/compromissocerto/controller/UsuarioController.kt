package lucasgodoy1.com.github.compromissocerto.controller

import android.app.Activity
import android.view.View
import lucasgodoy1.com.github.compromissocerto.ui.UsuarioActivity
import lucasgodoy1.com.github.compromissocerto.util.trocaDeTela
import lucasgodoy1.com.github.compromissocerto.view.UsuarioActivityComponente

class UsuarioController (var usuarioActivity: UsuarioActivity){
    var componenteUsuario = UsuarioActivityComponente(usuarioActivity)


    fun <R : Activity> btnAdcNovoCompromisso(destino: Class<R>) {
        componenteUsuario.btnAdicionar.setOnClickListener(View.OnClickListener {
            trocaDeTela(usuarioActivity, destino)

        })
    }



    fun <R : Activity>  btnMeusCompromissos(destino: Class<R>){
        componenteUsuario.btnMeusCompromissos.setOnClickListener(View.OnClickListener {
            trocaDeTela(usuarioActivity, destino)
        })
    }


}