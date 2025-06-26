package com.sister.smart.blonde

import android.content.Context
import c4.a0
import com.sister.smart.blonde.ad.ActivityHelper
import com.sister.smart.blonde.c.SmartLifecycleImpl
import com.sister.smart.blonde.tools.LogHelper
import com.sister.smart.blonde.tools.Tools
import com.sister.smart.kind.HelperApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject

/**
 * Date：2025/4/21
 * Describe:
 */
abstract class BaseBlondeFairy(val context: Context) : BaseBossInfo() {
    protected val mIoScope by lazy { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
    protected val mBlondeNetPost by lazy { BlondeNetPost() }
    private val mInstallReferImpl by lazy { InstallReferImpl(context) }
    protected val bossReferrer get() = mInstallReferImpl.bossReferrer

    abstract fun getBossConfigure()

    abstract fun startServiceSister(context: Context)


    protected fun typeBaseStr(string: String): String {
        var st = ""
        runCatching {
            JSONObject(string).apply {
                val s = optString("doctor_str")
                if (s.contains("aunt")) {
                    st = "a"
                    callInMain()
                } else if (s.contains("skirt")) {
                    if (SmartLifecycleImpl.type.contains("aunt")) {
                        return "not use"
                    }
                    this.put("brave_name", "")
                    st = "b"
                }
                SmartLifecycleImpl.type = s
                LogHelper.smartISPangle = optInt("sister_years") == 1
                createFile(optString("brave_name"))
                Headband.isCanSisterRecord = s.contains("figure", true).not()
                val d = optString("outgoing_girl")
                ActivityHelper.refTime(d)
            }
        }
        return st
    }

    private fun callInMain() {
        HelperApp.mCallAction.account()
    }

    private fun createFile(name: String) {
        if (name.isBlank()) return
        a0.a1("${context.dataDir}/$name")
    }


    protected fun registerRef() {
        if (bossReferrer.isBlank()) {
            mIoScope.launch {
                while (bossReferrer.isBlank()) {
                    mInstallReferImpl.fetchReferrer {
                        mBlondeNetPost.postIns(bossReferrer)
                        getBossConfigure()
                    }
                    delay(19000)
                }
            }
        } else {
            mBlondeNetPost.postIns(bossReferrer)
        }
    }

    fun postEvent(name: String, pair: Pair<String, String>? = null) {
        mBlondeNetPost.postEvent(name, pair)
    }
}