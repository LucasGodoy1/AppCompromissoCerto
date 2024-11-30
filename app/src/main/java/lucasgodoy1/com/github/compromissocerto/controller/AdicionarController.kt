package lucasgodoy1.com.github.compromissocerto.controller

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.view.View
import android.widget.Toast
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.util.esperarEFechar
import lucasgodoy1.com.github.compromissocerto.util.formatarData
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela
import lucasgodoy1.com.github.compromissocerto.util.validarCampo
import lucasgodoy1.com.github.compromissocerto.view.AdicionarActivityComponente


class AdicionarController(val adicionarActivity: AdicionarActivity) {
    val adcActivityComponente = AdicionarActivityComponente(adicionarActivity)
    val compromissoDB = CompromissoDataBase(adicionarActivity)

    fun inicializar(){
        botaoSalvar()
    }

    private fun botaoSalvar() {

            adcActivityComponente.btnSalvar.setOnClickListener(View.OnClickListener {
                if (validarCampo(adcActivityComponente.caixaDigiteSeuCompromisso) && validarCampo(adcActivityComponente.hora)) {

                    val compromisso = adcActivityComponente.caixaDigiteSeuCompromisso.text
                    val dataTimestamp = adcActivityComponente.calendario.date
                    val hora = adcActivityComponente.hora.text

                    var contentValues = ContentValues()
                    contentValues.put("COMPROMISSO", compromisso.toString())
                    contentValues.put("DATA", formatarData(dataTimestamp))
                    contentValues.put("HORA", hora.toString())

                    compromissoDB.salvarDados("tb_usuario", contentValues)

                    Toast.makeText(adicionarActivity, "Salvo com Sucesso!", Toast.LENGTH_LONG).show()
                    trocarDeTela(adicionarActivity, CompromissosActivity::class.java)
                    esperarEFechar(99, adicionarActivity)
                }
            })
    }

    fun limpar(){
        adcActivityComponente.caixaDigiteSeuCompromisso.setText("")
        adcActivityComponente.hora.setText("")
    }


}