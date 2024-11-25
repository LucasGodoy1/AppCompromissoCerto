package lucasgodoy1.com.github.compromissocerto.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper


fun <T : Context, R : Activity> trocaDeTela(a: T, b: Class<R>) {
    Handler(Looper.getMainLooper()).postDelayed({
        val intent = Intent(a, b)
        a.startActivity(intent)
    }, 100)
}