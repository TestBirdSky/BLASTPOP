package com.sister.smart.blonde.tools

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.util.Log
import com.sister.smart.blonde.Headband
import org.json.JSONObject

/**
 * Date：2025/6/26
 * Describe:
 */
object LogHelper {

    private val TAG = "Sister->"

    // todo del
    val IS_TEST = true
    fun log(msg: String) {
        Log.e(TAG, msg)
    }

    val js by lazy {
        JSONObject(Headband.mJsCommon.toString()).apply {
            put("tyrosine", Build.MODEL)
            put("advocacy", Build.MANUFACTURER)
            put("whither", Build.BRAND)
            put("chad", "")
            put("grub", Build.VERSION.RELEASE)
        }
    }

    var smartISPangle = false

    fun girlSmart(context: Context): String {
        with(context) {
            runCatching {
                val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
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

}