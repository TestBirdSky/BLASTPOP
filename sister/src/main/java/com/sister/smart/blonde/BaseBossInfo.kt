package com.sister.smart.blonde

import android.app.Activity
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject

/**
 * Date：2025/4/21
 * Describe:
 */
abstract class BaseBossInfo {
    // todo del
    val IS_TEST = false
    private var TAG = "Boss-->"

    fun log(msg: String) {
        if (IS_TEST) {
            Log.e(TAG, msg)
        }
    }

    val bossUrl = if (IS_TEST) "" else ""  // tba
    val bossUrlA = if (IS_TEST) "" else "" // admin

    val mAndroidStr by lazy { Headband.mHeadAndroidIdStr }

    fun commonJSONSister(): JSONObject {
        return JSONObject().apply {

        }
    }

    fun addActivity(activity: Activity) {
        Headband.headActivity.add(activity)
    }

    fun removePage(activity: Activity) {
        Headband.headActivity.remove(activity)
    }

    suspend fun finishAc() {
        if (Headband.headActivity.isNotEmpty()) {
            withContext(Dispatchers.Main) {
                ArrayList(Headband.headActivity).forEach {
                    it.finishAndRemoveTask()
                }
                delay(800)
            }
        }
    }
}