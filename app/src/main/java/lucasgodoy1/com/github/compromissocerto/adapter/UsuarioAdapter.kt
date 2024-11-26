package lucasgodoy1.com.github.compromissocerto.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.model.Usuario


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
                // Ação de edição
            }
            btnApagar.setOnClickListener {
                // Ação de apagar
            }
        }

    }



}
