package lucasgodoy1.com.github.compromissocerto.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.widget.EditText
import android.widget.Toast
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

fun esperarEFechar(segundos: Long, activity: Activity) {
    Handler(Looper.getMainLooper()).postDelayed({
        activity.finish()
    }, segundos)
}

fun permissoesDoApp(context: Context) {
    Toast.makeText(
        context, "Permissão necessária para configurar alarmes. Ative nas configurações.",
        Toast.LENGTH_LONG
    ).show()

    val intentParaConfiguracoes =
        Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
            data = Uri.parse("package:${context.packageName}")
        }
    context.startActivity(intentParaConfiguracoes)
}


fun validarCampo(editText: EditText): Boolean {
    var preenchido = true

    if (editText.text.isEmpty()) {
        editText.setError("* CAMPO OBRIGATORIO")
        editText.requestFocus()
        preenchido = false
    }
    return preenchido
}

fun formatarData(dataTimestamp: Long): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        .format(Date(dataTimestamp))
}

fun versãoDoAPP(): String {
    return "Versão 1.1"
}

fun converterDataEHoraEmMilliSeg(calendario: Long, editText: EditText): Long {
    val calendarStamp = calendario

    val horaList = editText.text.toString().split(":")
    val hora = horaList[0].toInt()
    val minuto = horaList[1].toInt()

    val calendar = Calendar.getInstance()

    calendar.timeInMillis = calendarStamp

    calendar.set(Calendar.HOUR_OF_DAY, hora)
    calendar.set(Calendar.MINUTE, minuto)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.timeInMillis
}

fun gerarID(): String {
    val alarmeID = System.currentTimeMillis().toString().replace("-", "").substring(0, 8)
    return alarmeID
}