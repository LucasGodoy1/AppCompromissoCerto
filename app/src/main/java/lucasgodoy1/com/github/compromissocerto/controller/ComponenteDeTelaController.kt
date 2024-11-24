package lucasgodoy1.com.github.compromissocerto.controller

import android.util.Log
import android.view.View
import lucasgodoy1.com.github.compromissocerto.ui.MainActivity
import lucasgodoy1.com.github.compromissocerto.view.ComponentesMainActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ComponenteDeTelaController(mainActivity: MainActivity) {
    val componenteMain = ComponentesMainActivity(mainActivity)


    fun botaoSalvar() {
        componenteMain.btnSalvar.setOnClickListener(View.OnClickListener {
            val compromisso = componenteMain.caixaDigiteSeuCompromisso.text
            val dataTimestamp = componenteMain.calendario.date
            val hora = componenteMain.hora.text

            val dataFormatada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(Date(dataTimestamp))


            Log.i("suaINFO_compromisso", compromisso.toString())
            Log.i("suaINFO_data", dataFormatada)
            Log.i("suaINFO_hora", hora.toString())
        })
    }


}