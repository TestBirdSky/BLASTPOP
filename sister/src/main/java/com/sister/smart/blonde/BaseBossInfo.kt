package com.sister.smart.blonde

import android.util.Log
import com.sister.smart.blonde.tools.LogHelper
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
        if (IS_TEST) "https://test-hustle.puzzlegamefinder.com/dovetail/toss/vest" else "https://hustle.puzzlegamefinder.com/unix/barrow/leftover"  // tba
    val bossUrlA =
        if (IS_TEST) "https://game.puzzlegamefinder.com/apitest/smart/girl/" else "https://game.puzzlegamefinder.com/api/smart/girl/" // admin

    val mAndroidStr by lazy { Headband.mHeadAndroidIdStr }

    fun commonJSONSister(): JSONObject {
        return JSONObject().apply {
            put("nettle", LogHelper.js.apply {
                put("posy", mAndroidStr)
                put("hubris", mAndroidStr)
                put("atomic", System.currentTimeMillis())
                put("chine", UUID.randomUUID().toString())
                put("caldwell", "_")
            })
        }
    }


    suspend fun finishAc() {
        if (Headband.headActivity.isNotEmpty()) {
            withContext(Dispatchers.Main) {
                ArrayList(Headband.headActivity).forEach {
                    it.finishAndRemoveTask()
                }
                delay(899)
            }
        }
    }
}