package com.sister.smart.blonde

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.os.Build
import android.webkit.WebView
import com.sister.smart.blonde.tools.LogHelper
import com.tencent.mmkv.MMKV

/**
 * Date：2025/4/21
 * Describe:
 */
class GirlCenter {
    fun check(context: Context): String {
        MMKV.initialize(context)
        if (isProgress(context)) {
            Headband.mHeadApp = context as Application
            AngelHelper.sisterInit(context)
            return "sister"
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val processName: String = LogHelper.girlSmart(context)
                if (processName.isNotBlank()) {
                    WebView.setDataDirectorySuffix(processName)
                }
            }
        }
        return ""
    }

    private fun isProgress(context: Context): Boolean {
        return context.packageName == LogHelper.girlSmart(context)
    }

    private fun initNotification(context: Context){
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                "Sister_19", "GameChannel", NotificationManager.IMPORTANCE_DEFAULT
            )
            (context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }
    }
}