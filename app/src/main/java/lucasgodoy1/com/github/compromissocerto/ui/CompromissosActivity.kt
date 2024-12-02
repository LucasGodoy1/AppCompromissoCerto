package lucasgodoy1.com.github.compromissocerto.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lucasgodoy1.com.github.compromissocerto.R
import lucasgodoy1.com.github.compromissocerto.adapter.UsuarioAdapter
import lucasgodoy1.com.github.compromissocerto.controller.CompromissoController
import lucasgodoy1.com.github.compromissocerto.data.CompromissoDataBase
import lucasgodoy1.com.github.compromissocerto.model.Usuario
import lucasgodoy1.com.github.compromissocerto.util.TAG
import lucasgodoy1.com.github.compromissocerto.util.trocarDeTela

class CompromissosActivity : AppCompatActivity() {
    lateinit var aListaDeUsuarios: List<Usuario>
    lateinit var usuarioAdapter: UsuarioAdapter
    lateinit var compromissoDB: CompromissoDataBase
    lateinit var recycleView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_compromissos)

        Log.w(TAG, "CompromissosActivity Iniciada")

        compromissoDB = CompromissoDataBase(this)
        aListaDeUsuarios = compromissoDB.listaDados()

        if (aListaDeUsuarios.isNotEmpty()) {
            usuarioAdapter = UsuarioAdapter(aListaDeUsuarios, this)

            recycleView = findViewById(R.id.idRecyclerView)
            recycleView.layoutManager = LinearLayoutManager(this)
            recycleView.adapter = usuarioAdapter

            val compromissoController = CompromissoController(this)
            compromissoController.inicializar()

        } else {
            Toast.makeText(
                this,
                "Você Ainda não tem uma Lista De Compromisso", Toast.LENGTH_SHORT
            ).show()
            this.finish()
            trocarDeTela(this, AdicionarActivity::class.java)
        }


    }
}