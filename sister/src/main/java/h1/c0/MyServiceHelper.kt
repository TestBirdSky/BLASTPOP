package h1.c0

import android.app.Service
import android.content.Intent
import android.os.IBinder
import h1.m0.r1.Helper

/**
 * Date：2025/6/26
 * Describe:
 * JE
 */
class MyServiceHelper : Service() {
    private val mHelper by lazy { Helper() }
    override fun onBind(intent: Intent?): IBinder? {
        return mHelper.c(this)
    }

}