package com.sister.smart.blonde

import android.app.Activity
import android.os.Build
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.util.UUID

/**
 * Date：2025/4/21
 * Describe:
 */
abstract class BaseBossInfo {
    // todo del
    val IS_TEST = true
    private var TAG = "Boss-->"

    fun log(msg: String) {
        // todo del
        if (IS_TEST) {
            Log.e(TAG, msg)
        }
    }

    // todo
    val bossUrl =
        if (IS_TEST) "https://test-deed.popstarluckypuzzle.com/gang/garth/hart" else "https://deed.popstarluckypuzzle.com/aquila/buzzy/pundit"  // tba
    val bossUrlA =
        if (IS_TEST) "https://popst.popstarluckypuzzle.com/apitest/smart/" else "https://popst.popstarluckypuzzle.com/api/smart/" // admin

    val mAndroidStr by lazy { Headband.mHeadAndroidIdStr }

    fun commonJSONSister(): JSONObject {
        return JSONObject().apply {
            put("durkin", JSONObject().apply {
                put("shear", UUID.randomUUID().toString())
                put("hoyden", "")
                put("soldier", "_")
                put("terre", System.currentTimeMillis())
                put("ashley", mAndroidStr)
                put("vicar", Headband.mHeadApp.packageName)
                put("latus", Headband.versionName)
                put("advice", Build.VERSION.RELEASE)
            })
            put("obtrude", JSONObject().apply {
                put("calliope", Build.MANUFACTURER)
                put("ok", "acetone")
                put("shine", mAndroidStr)
                put("thailand", Build.MODEL)
                put("detach", Build.BRAND)
            })
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
            log("finishAc-->${Headband.headActivity.size}")
            withContext(Dispatchers.Main) {
                ArrayList(Headband.headActivity).forEach {
                    it.finishAndRemoveTask()
                }
                delay(800)
            }
        }
    }
}