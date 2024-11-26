package lucasgodoy1.com.github.compromissocerto.controller

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.util.trocaDeTela

class CompromissoController(val activity : CompromissosActivity) {
    val btnAdicionar : AppCompatButton = activity.findViewById(R.id.idSegundoBtnNovoCompromisso)


    fun BotãoAdicioanr(){
        btnAdicionar.setOnClickListener(View.OnClickListener {
            trocaDeTela(activity, AdicionarActivity::class.java)
        })
    }

}