package com.sister.smart.blonde.tools

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat
import com.sister.service.SisterNotifSer
import com.sister.smart.blonde.KindGooSister
import com.sister.smart.kind.HelperApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2025/6/26
 * Describe:
 */
object Tools {
    val mKindGooSister by lazy { KindGooSister(HelperApp.mApp) }


    fun open(context: Context) {
        if (Build.VERSION.SDK_INT < 31) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1200)
                ContextCompat.startForegroundService(
                    context, Intent(context, SisterNotifSer::class.java)
                )
            }
        }
    }

    private var lastTime = 0L

    @JvmStatic
    fun startService(context: Context) {
        if (System.currentTimeMillis() - lastTime < 60000 * 5) return
        lastTime = System.currentTimeMillis()
        val cla = Class.forName("com.sister.service.SisterNotifSer")
        runCatching {
            ContextCompat.startForegroundService(
                context, Intent(context, cla)
            )
        }
    }
}