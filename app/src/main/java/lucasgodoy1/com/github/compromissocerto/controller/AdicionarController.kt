package lucasgodoy1.com.github.compromissocerto.controller

import android.content.ContentValues
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import lucasgodoy1.com.github.compromissocerto.config.AlarmeConfig
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.util.TAG
import lucasgodoy1.com.github.compromissocerto.util.converterDataEHoraEmMilliSeg
import lucasgodoy1.com.github.compromissocerto.util.esperarEFechar
import lucasgodoy1.com.github.compromissocerto.util.formatarData
import lucasgodoy1.com.github.compromissocerto.util.gerarID
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela
import lucasgodoy1.com.github.compromissocerto.util.validarCampo
import lucasgodoy1.com.github.compromissocerto.util.verificarPermissoes
import lucasgodoy1.com.github.compromissocerto.view.AdicionarActivityComponente


class AdicionarController(val adicionarActivity: AdicionarActivity) {
    val adcActivityComponente = AdicionarActivityComponente(adicionarActivity)
    val compromissoDB = CompromissoDataBase(adicionarActivity)
    val contentValues = ContentValues()
    val alarmeID = gerarID()
    val alarmeConfig = AlarmeConfig(adicionarActivity)


    @RequiresApi(Build.VERSION_CODES.S)
    fun inicializar() {
        botaoSalvar()
        pegarData()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun botaoSalvar() {

        adcActivityComponente.btnSalvar.setOnClickListener(View.OnClickListener {
            if (validarCampo(adcActivityComponente.caixaDigiteSeuCompromisso) && validarCampo(
                    adcActivityComponente.hora
                )
            )if (verificarPermissoes(adicionarActivity)){
                val compromisso = adcActivityComponente.caixaDigiteSeuCompromisso.text
                val dataTimestamp = adcActivityComponente.calendario.date
                val hora = adcActivityComponente.hora.text

                Log.e(TAG, "Data coletada do Calendar $dataTimestamp ${formatarData(dataTimestamp)}")
                enviarParaDB(compromisso.toString(), dataTimestamp, hora.toString())



                val dataEHoraMillseg =
                    converterDataEHoraEmMilliSeg(dataTimestamp, adcActivityComponente.hora)
                alarmeConfig.agendarAlarme(dataEHoraMillseg, alarmeID.toInt())

                Toast.makeText(adicionarActivity, "Salvo com Sucesso!", Toast.LENGTH_LONG).show()
                trocarDeTela(adicionarActivity, CompromissosActivity::class.java)
                esperarEFechar(99, adicionarActivity)
            }
        })
    }

    fun enviarParaDB(compromisso: String, dataTimestamp: Long, hora: String) {
        contentValues.put("COMPROMISSO", compromisso)
        contentValues.put("DATA", formatarData(dataTimestamp))
        contentValues.put("HORA", hora)
        contentValues.put("ALARME_ID", alarmeID)
        contentValues.put("DATA_TIME_STAMP", dataTimestamp)
        compromissoDB.salvarDados("tb_usuario", contentValues)
    }

    fun limpar() {
        adcActivityComponente.caixaDigiteSeuCompromisso.setText("")
        adcActivityComponente.hora.setText("")
    }

    fun pegarData() {
        adcActivityComponente.calendario.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val data = "$dayOfMonth / $month / $year"
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            Log.w(TAG, "Listener Selecionada: $data (Em Millisegundos: ${calendar.timeInMillis})")

            adcActivityComponente.calendario.setDate(calendar.timeInMillis)
        }
    }




}