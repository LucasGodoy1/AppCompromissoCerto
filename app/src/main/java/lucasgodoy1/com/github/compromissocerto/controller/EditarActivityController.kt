package lucasgodoy1.com.github.compromissocerto.controller

import android.content.ContentValues
import android.content.Context
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import lucasgodoy1.com.github.compromissocerto.config.AlarmeConfig
import lucasgodoy1.com.github.compromissocerto.datasource.AppDataBase
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.ui.EditarActivity
import lucasgodoy1.com.github.compromissocerto.util.TAG
import lucasgodoy1.com.github.compromissocerto.util.converterDataEHoraEmMilliSeg
import lucasgodoy1.com.github.compromissocerto.util.formatarData
import lucasgodoy1.com.github.compromissocerto.util.gerarID
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela
import lucasgodoy1.com.github.compromissocerto.util.validarCampo
import lucasgodoy1.com.github.compromissocerto.view.EditarActivityComponente

class EditarActivityController(val editarActivity: EditarActivity) {
    val editarActivityComponente = EditarActivityComponente(editarActivity)
    val preferences = editarActivity.getSharedPreferences("EDITAR_TEMP", Context.MODE_PRIVATE)

    val compromissoDataBase = AppDataBase(editarActivity)

    var contentValues = ContentValues()
    val alarmeConfig = AlarmeConfig(editarActivity)

    //TODO: adicionar um throw
    val id = preferences.getString("ID", "") ?: ""
    val compromisso = preferences.getString("COMPROMISSO", "") ?: ""
    val data = preferences.getString("DATA", "") ?: ""
    val hora = preferences.getString("HORA", "") ?: ""
    val alarmeIDAtual = preferences.getString("ALARME_ID", "") ?: ""
    val dataTimeStamppref = preferences.getString("DATA_TIME_STAMP", "") ?: ""

    val novoAlarmeID = gerarID()

    @RequiresApi(Build.VERSION_CODES.S)
    fun inicializar() {
        exibirSeuCompromissoAtual()
        botaoSalvarTelaEditar()
        pegarData()
    }

    private fun exibirSeuCompromissoAtual() {
        editarActivityComponente.editCompromisso.setText(compromisso)
        editarActivityComponente.editHora.setText(hora)
        editarActivityComponente.editCalendario.setDate(dataTimeStamppref.toLong())
    }


    @RequiresApi(Build.VERSION_CODES.S)
    private fun botaoSalvarTelaEditar() {
        editarActivityComponente.btnSalvar.setOnClickListener {

            if (validarCampo(editarActivityComponente.editCompromisso) && validarCampo(
                    editarActivityComponente.editHora
                )
            ) {

                alarmeConfig.excluirAlarme(alarmeIDAtual.toInt())
                agendarNovoAlarme()

                contentValues.put(
                    "COMPROMISSO",
                    editarActivityComponente.editCompromisso.text.toString()
                )
                contentValues.put(
                    "DATA",
                    formatarData(editarActivityComponente.editCalendario.date)
                )
                contentValues.put("HORA", editarActivityComponente.editHora.text.toString())
                contentValues.put("ALARME_ID", novoAlarmeID)

                if (id.isNotEmpty()) {
                    val rowsUpdated = compromissoDataBase.atualizarDados(id.toInt(), contentValues)
                    if (rowsUpdated > 0) {
                        Toast.makeText(
                            editarActivity,
                            "Suas Alterações foram Salvas com Sucesso",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                preferences.edit().clear().apply()
                editarActivity.finish()
                trocarDeTela(editarActivity, CompromissosActivity::class.java)
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.S)
    fun agendarNovoAlarme(){
        val dataEHoraMillseg =
            converterDataEHoraEmMilliSeg(editarActivityComponente.editCalendario.date, editarActivityComponente.editHora)
        alarmeConfig.agendarAlarme(dataEHoraMillseg, novoAlarmeID.toInt())
        Log.i(TAG, "Novo Alarme Definido")
    }

    fun pegarData() {
        editarActivityComponente.editCalendario.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val data = "$dayOfMonth / $month / $year"
            val calendar = Calendar.getInstance().apply {
                set(year, month, dayOfMonth, 0, 0, 0)
                set(Calendar.MILLISECOND, 0)
            }
            Log.w(TAG, "Listener Selecionada: $data (Em Millisegundos: ${calendar.timeInMillis})")

            editarActivityComponente.editCalendario.setDate(calendar.timeInMillis)
        }
    }


}