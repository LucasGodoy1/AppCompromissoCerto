package lucasgodoy1.com.github.compromissocerto.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

val TAG = "Log_AppCompromissoCerto"

fun <T : Context, R : Activity> trocarDeTela(a: T, b: Class<R>) {
    Handler(Looper.getMainLooper()).postDelayed({
        val intent = Intent(a, b)
        a.startActivity(intent)
    }, 100)
}

fun esperarEFechar( segundos: Long, activity : Activity) {
    Handler(Looper.getMainLooper()).postDelayed({
        activity.finish()
    }, segundos)
}


fun validarCampo(editText : EditText): Boolean {
    var preenchido = true

    if (editText.text.isEmpty()) {
        editText.setError("* CAMPO OBRIGATORIO")
        editText.requestFocus()
        preenchido = false
    }
    return preenchido
}

fun formatarData(dataTimestamp : Long) : String{
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        .format(Date(dataTimestamp))
}

fun versãoDoAPP(): String{
    return "Versão 1.1"
}