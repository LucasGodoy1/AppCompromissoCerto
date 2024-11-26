package lucasgodoy1.com.github.compromissocerto.ui

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.adapter.UsuarioAdapter
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.model.Usuario

class CompromissosActivity : AppCompatActivity() {
    lateinit var aListaDeUsuarios : List<Usuario>
    lateinit var usuarioAdapter: UsuarioAdapter
    lateinit var compromissoDB : CompromissoDataBase
    lateinit var recycleView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_compromissos)

        compromissoDB = CompromissoDataBase(this)
        aListaDeUsuarios = compromissoDB.listaDados()

        if (aListaDeUsuarios.isNotEmpty()) {
            usuarioAdapter = UsuarioAdapter(aListaDeUsuarios, this)
        }

        recycleView = findViewById(R.id.idRecyclerView)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = usuarioAdapter
    }
}