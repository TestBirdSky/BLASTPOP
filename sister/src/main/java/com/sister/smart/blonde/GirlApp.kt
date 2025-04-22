package com.sister.smart.blonde

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.webkit.WebView
import com.tencent.mmkv.MMKV

/**
 * Date：2025/4/21
 * Describe:
 */
abstract class GirlApp : Application() {

    companion object {
        lateinit var mGirlMMKV: MMKV
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val processName: String = getProcessName()
            if (!packageName.equals(processName)) {
                WebView.setDataDirectorySuffix(processName)
            }
        }
        if (isProgress(this)) {
            System.loadLibrary("smart")
            MMKV.initialize(this)
            mGirlMMKV = MMKV.defaultMMKV()
            Headband.mHeadApp = this
            AngelHelper.sisterInit(this)
        }
    }

    private fun isProgress(context: Context): Boolean {
        return context.packageName == context.girlSmart()
    }

    private fun Context.girlSmart(): String {
        runCatching {
            val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            val runningApps = am.runningAppProcesses ?: return ""
            for (info in runningApps) {
                when (info.pid) {
                    android.os.Process.myPid() -> return info.processName
                }
            }
        }
        return ""
    }
}