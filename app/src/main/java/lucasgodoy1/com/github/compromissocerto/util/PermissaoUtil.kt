package lucasgodoy1.com.github.compromissocerto.util

import android.app.Activity
import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi

val TAG_PERMISSOES = "Log_Permissoes_CompromissoCerto"


fun verificarMostrarTelaDeBloqueio(context: Context): Boolean {
    val flags = (context as Activity).window.attributes.flags
    val ativo = flags and WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED != 0
    if (!ativo) {
        Log.w(TAG_PERMISSOES, "Permissao para mostrar na tela de bloqueio ainda não configurada.")
    } else {
        Log.i(TAG_PERMISSOES, "Permissao para mostrar na tela de bloqueio já está ativa.")
    }
    return ativo
}




// 2. Abrir nova janela (SYSTEM_ALERT_WINDOW)
fun verificarAbrirNovaJanela(context: Context): Boolean {
    val permitido = Settings.canDrawOverlays(context)
    if (!permitido) {
        Log.w(TAG_PERMISSOES, "Permissao SYSTEM_ALERT_WINDOW necessária. Solicite ao usuário.")
    } else {
        Log.i(TAG_PERMISSOES, "Permissao SYSTEM_ALERT_WINDOW já configurada.")
    }
    return permitido
}

fun solicitarAbrirNovaJanela(context: Context) {
    if (!verificarAbrirNovaJanela(context)) {
        Log.w(TAG_PERMISSOES, "Solicitando permissao SYSTEM_ALERT_WINDOW para abrir nova janela.")
        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION).apply {
            data = Uri.parse("package:${context.packageName}")
        }
        context.startActivity(intent)
    }
}

// --- PERMISSÕES EXISTENTES ---

fun verificarExactAlarm(context: Context): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val gerenciadorDeAlarmes = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val permitido = gerenciadorDeAlarmes.canScheduleExactAlarms()
        if (!permitido) {
            Log.w(TAG_PERMISSOES, "Permissao EXACT_ALARM necessária. Solicite ao usuário.")
        } else {
            Log.i(TAG_PERMISSOES, "Permissao EXACT_ALARM já configurada.")
        }
        permitido
    } else {
        Log.i(TAG_PERMISSOES, "Permissao EXACT_ALARM não é necessária nesta versão do Android.")
        true
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun solicitarExactAlarm(context: Context) {
    if (!verificarExactAlarm(context)) {
        Log.w(TAG_PERMISSOES, "Solicitando permissao EXACT_ALARM.")
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
            data = Uri.parse("package:${context.packageName}")
        }
        context.startActivity(intent)
    }
}

@RequiresApi(Build.VERSION_CODES.S)
fun verificarPermissoes(context: Context): Boolean {
    var todasPermissoesConcedidas = true

    if (!verificarExactAlarm(context)) {
        todasPermissoesConcedidas = false
        solicitarExactAlarm(context)
    }

    if (!verificarAbrirNovaJanela(context)) {
        todasPermissoesConcedidas = false
        solicitarAbrirNovaJanela(context)
    }


    Log.i(TAG_PERMISSOES, "Verificacao de permissoes concluida. Todas concedidas: $todasPermissoesConcedidas")
    return todasPermissoesConcedidas
}
