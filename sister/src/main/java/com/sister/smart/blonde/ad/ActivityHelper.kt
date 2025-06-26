package com.sister.smart.blonde.ad

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.sister.smart.blonde.BaseAcHelper
import com.sister.smart.blonde.BlondeNetPost
import com.sister.smart.blonde.Headband
import com.sister.smart.blonde.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * Date：2025/6/26
 * Describe:
 */
class ActivityHelper : BaseAcHelper() {
    private var isPInfo = false

    var isFront = false

    companion object {
        private var timeSister = 1000L
        private var angelTime = 1200L
        fun refTime(d: String) {
            if (d.contains("-")) {
                timeSister = d.split("-")[0].toLong()
                angelTime = d.split("-")[1].toLong()
            }
        }
    }


    fun action(isP: Boolean, activity: Activity, mBlondeNetPost: BlondeNetPost, call: () -> Unit) {
        if (isP && activity is AppCompatActivity) {
            isPInfo = true
            activity.lifecycleScope.launch {
                val time = Random.nextLong(timeSister, angelTime)
                mBlondeNetPost.postEvent("starup", Pair("time", "${Math.round(time / 1000.0)}"))
                delay(time)
                mBlondeNetPost.postEvent("delaytime", Pair("time", "$time"))
                call.invoke()
            }
        } else {
            if (isFront.not()) {
                val name = activity::class.java.canonicalName ?: ""
                if (name == "com.unity3d.player.UnityPlayerActivity") {
                    isFront = true
                    mBlondeNetPost.postEvent(
                        "session_front", Pair(
                            "time",
                            "${(System.currentTimeMillis() - Headband.appInstallTime) / 1000}"
                        )
                    )
                }
            }
        }
        if (isPInfo) {
            a(activity)
        }
    }

    override fun inName(): Int {
        return R.string.tt_tips_if
    }

    override fun idIcon(): Int {
        return R.mipmap.ic_tt_icon
    }
}