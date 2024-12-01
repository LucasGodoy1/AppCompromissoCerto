package lucasgodoy1.com.github.compromissocerto.controller

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.ui.EditarActivity
import lucasgodoy1.com.github.compromissocerto.util.formatarData
import lucasgodoy1.com.github.compromissocerto.util.validarCampo
import lucasgodoy1.com.github.compromissocerto.view.EditarActivityComponente

class EditarActivityController(val editarActivity: EditarActivity) {
    val editarActivityComponente = EditarActivityComponente(editarActivity)
    val preferences = editarActivity.getSharedPreferences("EDITAR_TEMP", Context.MODE_PRIVATE)

    val compromissoDataBase = CompromissoDataBase(editarActivity)

    var contentValues = ContentValues()

    val id = preferences.getString("ID", "") ?: ""
    val compromisso = preferences.getString("COMPROMISSO", "") ?: ""
    val data = preferences.getString("DATA", "") ?: ""
    val hora = preferences.getString("HORA", "") ?: ""


    fun inicializar() {
        seuCompromissoAtualEdit()
        botaoSalvarTelaEditar()
    }

    private fun seuCompromissoAtualEdit() {
        editarActivityComponente.editCompromisso.setText(compromisso)
        editarActivityComponente.editHora.setText(hora)
    }


    private fun botaoSalvarTelaEditar() {

        editarActivityComponente.btnSalvar.setOnClickListener {
            if (validarCampo(editarActivityComponente.editCompromisso) && validarCampo(
                    editarActivityComponente.editHora
                )
            ) {

                contentValues.put(
                    "COMPROMISSO",
                    editarActivityComponente.editCompromisso.text.toString()
                )
                contentValues.put(
                    "DATA",
                    formatarData(editarActivityComponente.editCalendario.date)
                )
                contentValues.put("HORA", editarActivityComponente.editHora.text.toString())

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
            }
        }

    }


}