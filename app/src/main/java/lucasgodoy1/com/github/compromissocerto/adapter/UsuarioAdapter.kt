package lucasgodoy1.com.github.compromissocerto.adapter

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.model.Usuario
import lucasgodoy1.com.github.compromissocerto.ui.EditarActivity
import lucasgodoy1.com.github.compromissocerto.util.trocaDeTela



class UsuarioAdapter(var aUsuarios : List<Usuario>, val context : Context) : RecyclerView.Adapter<UsuarioAdapter.ViewHolder>() {



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
                editor.apply()


                trocaDeTela(context, EditarActivity::class.java)
                (context as Activity).finish()
            }


            btnApagar.setOnClickListener {
                // Ação de apagar
            }
        }

    }



}
