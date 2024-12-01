package lucasgodoy1.com.github.compromissocerto.controller

import android.content.ContentValues
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import lucasgodoy1.com.github.compromissocerto.config.AlarmeConfig
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.util.converterDataEHoraEmMilliSeg
import lucasgodoy1.com.github.compromissocerto.util.esperarEFechar
import lucasgodoy1.com.github.compromissocerto.util.formatarData
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela
import lucasgodoy1.com.github.compromissocerto.util.validarCampo
import lucasgodoy1.com.github.compromissocerto.view.AdicionarActivityComponente


class AdicionarController(val adicionarActivity: AdicionarActivity) {
    val adcActivityComponente = AdicionarActivityComponente(adicionarActivity)
    val compromissoDB = CompromissoDataBase(adicionarActivity)
    val contentValues = ContentValues()

    val alarmeConfig = AlarmeConfig(adicionarActivity)



    @RequiresApi(Build.VERSION_CODES.S)
    fun inicializar(){
        botaoSalvar()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun botaoSalvar() {

            adcActivityComponente.btnSalvar.setOnClickListener(View.OnClickListener {
                if (validarCampo(adcActivityComponente.caixaDigiteSeuCompromisso) && validarCampo(adcActivityComponente.hora)) {

                    val compromisso = adcActivityComponente.caixaDigiteSeuCompromisso.text
                    val dataTimestamp = adcActivityComponente.calendario.date
                    val hora = adcActivityComponente.hora.text

                    enviarParaDB(compromisso.toString(), dataTimestamp, hora.toString())
                    val dataEHoraMillseg = converterDataEHoraEmMilliSeg(dataTimestamp, adcActivityComponente.hora)
                    alarmeConfig.agendarAlarme(dataEHoraMillseg)
                    //TODO: Identificar cada alarme individualmente por um id
                    //TODO: adicioanr novo campo no DB referente ao id do alarme

                    Toast.makeText(adicionarActivity, "Salvo com Sucesso!", Toast.LENGTH_LONG).show()
                    trocarDeTela(adicionarActivity, CompromissosActivity::class.java)
                    esperarEFechar(99, adicionarActivity)
                }
            })
    }

    fun enviarParaDB(compromisso : String, dataTimestamp : Long, hora : String){
        contentValues.put("COMPROMISSO", compromisso.toString())
        contentValues.put("DATA", formatarData(dataTimestamp))
        contentValues.put("HORA", hora.toString())
        compromissoDB.salvarDados("tb_usuario", contentValues)
    }

    fun limpar(){
        adcActivityComponente.caixaDigiteSeuCompromisso.setText("")
        adcActivityComponente.hora.setText("")
    }


}