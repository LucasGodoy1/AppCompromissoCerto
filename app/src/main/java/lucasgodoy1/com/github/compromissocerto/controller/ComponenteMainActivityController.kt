package lucasgodoy1.com.github.compromissocerto.controller

import android.content.ContentValues
import android.util.Log
import android.view.View
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.ui.MainActivity
import lucasgodoy1.com.github.compromissocerto.view.ComponenteMainActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ComponenteMainActivityController(mainActivity: MainActivity) {
    val componenteMain = ComponenteMainActivity(mainActivity)
    val compromissoDB = CompromissoDataBase(mainActivity)

    fun botaoSalvar() {
        componenteMain.btnSalvar.setOnClickListener(View.OnClickListener {
            val compromisso = componenteMain.caixaDigiteSeuCompromisso.text
            val dataTimestamp = componenteMain.calendario.date
            val hora = componenteMain.hora.text

            val dataFormatada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                .format(Date(dataTimestamp))

            var contentValues = ContentValues()
            contentValues.put("COMPROMISSO", compromisso.toString())
            contentValues.put("DATA", dataFormatada.toString())
            contentValues.put("HORA", hora.toString())

            compromissoDB.salvarDados("tb_usuario", contentValues)

        })
    }


}