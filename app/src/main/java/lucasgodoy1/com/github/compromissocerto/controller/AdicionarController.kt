package lucasgodoy1.com.github.compromissocerto.controller

import android.content.ContentValues
import android.view.View
import android.widget.Toast
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.ui.AdicionarActivity
import lucasgodoy1.com.github.compromissocerto.view.MainActivityComponente
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AdicionarController(val adicionarActivity: AdicionarActivity) {
    val componenteMain = MainActivityComponente(adicionarActivity)
    val compromissoDB = CompromissoDataBase(adicionarActivity)

    fun botaoSalvar() {

            componenteMain.btnSalvar.setOnClickListener(View.OnClickListener {
                if (validarCampo()) {
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

                    Toast.makeText(adicionarActivity, "Salvo com Sucesso!", Toast.LENGTH_LONG).show()
                    limpar()
                }else{
                    Toast.makeText(adicionarActivity, "Preencha os campos Obrigatios", Toast.LENGTH_SHORT).show()
                }

            })

    }

    fun limpar(){
        componenteMain.caixaDigiteSeuCompromisso.setText("")
        componenteMain.hora.setText("")
    }


    fun validarCampo(): Boolean {
        var preenchido = true

        if (componenteMain.caixaDigiteSeuCompromisso.text.isEmpty()) {
            componenteMain.caixaDigiteSeuCompromisso.setError("* CAMPO OBRIGATORIO")
            componenteMain.caixaDigiteSeuCompromisso.requestFocus()
            preenchido = false
        } else if (componenteMain.hora.text.isEmpty()) {
            componenteMain.hora.setError("* CAMPO OBRIGATORIO")
            componenteMain.hora.requestFocus()
            preenchido = false
        }
        return preenchido

    }


}