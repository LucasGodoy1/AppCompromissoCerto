package lucasgodoy1.com.github.compromissocerto.controller

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.util.esperarEFechar
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela

class CompromissoController(val activity: CompromissosActivity) {
    val btnAdicionar: AppCompatButton = activity.findViewById(R.id.idSegundoBtnNovoCompromisso)


    fun inicializar() {
        botãoAdicioanr()
    }

    private fun botãoAdicioanr() {
        btnAdicionar.setOnClickListener(View.OnClickListener {
            trocarDeTela(activity, AdicionarActivity::class.java)
            esperarEFechar(99, activity)
        })
    }

}