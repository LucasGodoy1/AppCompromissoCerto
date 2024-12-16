package lucasgodoy1.com.github.compromissocerto.adapter

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.config.AlarmeConfig
import lucasgodoy1.com.github.compromissocerto.datasource.AppDataBase
import lucasgodoy1.com.github.compromissocerto.model.Usuario
import lucasgodoy1.com.github.compromissocerto.ui.CompromissosActivity
import lucasgodoy1.com.github.compromissocerto.ui.EditarActivity
import lucasgodoy1.com.github.compromissocerto.util.TAG
import lucasgodoy1.com.github.compromissocerto.util.esperarEFechar
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela


class UsuarioAdapter(var aUsuarios: List<Usuario>, val context: Context) :
    RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val contexto = parent.context
        val inflater = LayoutInflater.from(contexto)
        val layoutDaLista = inflater.inflate(R.layout.list_item, parent, false)
        val viewHolder = ViewHolder(layoutDaLista)

        return viewHolder
    }

    override fun onBindViewHolder(holder: UsuarioAdapter.ViewHolder, position: Int) {
        val usuario = aUsuarios[position]
        holder.bind(usuario)
    }

    override fun getItemCount(): Int {
        return aUsuarios.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtDataEHora: TextView = itemView.findViewById(R.id.idDataEHora)
        val txtNomeDoCompromisso: TextView = itemView.findViewById(R.id.idNomeCompromisso)
        val btnEditar: AppCompatButton = itemView.findViewById(R.id.idBtnEditar)
        val btnApagar: AppCompatButton = itemView.findViewById(R.id.idBtnLixeira)


        @RequiresApi(Build.VERSION_CODES.S)
        fun bind(usuario: Usuario) {
            txtNomeDoCompromisso.text = usuario.compromisso
            txtDataEHora.text = "Dia ${usuario.data} às ${usuario.hora}"

            btnEditar.setOnClickListener {
                val preferences = context.getSharedPreferences("EDITAR_TEMP", Context.MODE_PRIVATE)
                val editor = preferences.edit()

                editor.putString("ID", usuario.id.toString())
                editor.putString("COMPROMISSO", usuario.compromisso)
                editor.putString("DATA", usuario.data)
                editor.putString("HORA", usuario.hora)
                editor.putString("ALARME_ID", usuario.alarmeId)
                editor.putString("DATA_TIME_STAMP", usuario.dataTimeStamp.toString())
                editor.apply()


                trocarDeTela(context, EditarActivity::class.java)
                (context as Activity).finish()
            }


            btnApagar.setOnClickListener {
                val db = AppDataBase(context)
                val alerta = android.app.AlertDialog.Builder(context)

                alerta.setTitle("Confirmar Exclusão")
                alerta.setMessage("Essa ação irá apagar este compromisso. Você deseja continuar?")

                alerta.setPositiveButton("Sim") { _, _ ->
                    val resultado = db.deletarUsuario(usuario.id)
                    val alarmeConfig = AlarmeConfig(context)

                    val alarmeID = usuario.alarmeId?.toInt() ?: Log.e(TAG, "ERRO! Alarme ID esta nullo")
                    alarmeConfig.excluirAlarme(alarmeID)

                    if (resultado > 0) {
                        Log.i(TAG, "Compromisso Excluido")
                        Toast.makeText(context, "Apagado com sucesso!", Toast.LENGTH_SHORT).show()
                        esperarEFechar(99, (context as Activity))
                        trocarDeTela(context, CompromissosActivity::class.java)
                    }
                }
                alerta.setNegativeButton("Não") { dialog, _ ->
                    dialog.dismiss()
                }
                alerta.show()
            }

        }

    }


}
