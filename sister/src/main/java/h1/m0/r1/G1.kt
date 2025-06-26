package h1.m0.r1

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Date：2025/6/26
 * Describe:
 * JE
 */
class G1 : Service() {
    private val mHelper by lazy { Helper() }
    override fun onBind(intent: Intent?): IBinder? {
        return mHelper.c(this)
    }

}