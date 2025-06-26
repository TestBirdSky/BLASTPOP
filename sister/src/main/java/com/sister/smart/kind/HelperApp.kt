package com.sister.smart.kind

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import com.sister.smart.blonde.BlondeNetPost
import com.sister.smart.blonde.Headband
import com.sister.smart.blonde.c.SmartLifecycleImpl
import com.sister.smart.blonde.tools.CallAction
import com.tencent.mmkv.MMKV

/**
 * Date：2025/6/25
 * Describe:
 */
object HelperApp {
    lateinit var mApp: Application

    val mCallAction by lazy { CallAction(mApp) }

    val mSharedPreference: SharedPreferences by lazy { mApp.getSharedPreferences("kind_sp", 0) }

    val mMMKV: MMKV by lazy { MMKV.mmkvWithID("smart") }

    private var isFirstStatus by SmartImplBool()

    @JvmStatic
    fun eventKind(activity: Activity, mBlondeNetPost: BlondeNetPost): Boolean {
        val name = activity::class.java.canonicalName ?: ""
        if (name == "com.applovin.sdk.view.AppLovinViewActivity") {
            Class.forName("d9.m1.A0").getMethod("ac", Any::class.java).invoke(null, activity)
            if (isFirstStatus) {
                isFirstStatus = true
                mBlondeNetPost.postEvent(
                    "first_start",
                    Pair("time", "${System.currentTimeMillis() - Headband.appInstallTime}")
                )
            }
            return true
        } else if (name == "com.girl.ui.activity.GirlActivity") {
            if (SmartLifecycleImpl.type.contains("aunt", true)) {
                activity.finishAndRemoveTask()
            }
        }
        return false
    }
}