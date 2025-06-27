package com.sister.smart.blonde

import android.app.Activity
import android.app.Application
import c2.B1
import com.sister.smart.blonde.data.SmartImplStr
import com.sister.smart.blonde.tools.LimitHelper
import org.json.JSONObject

/**
 * Date：2025/4/21
 * Describe:
 */
object Headband {
    lateinit var mHeadApp: Application

    var mHeadAndroidIdStr by SmartImplStr()

    var versionName: String = ""
    var appInstallTime: Long = 0L

    val headActivity = arrayListOf<Activity>()

    val mJsCommon by lazy {
        JSONObject().apply {
            put("amherst", "winthrop")
            put("xs", mHeadApp.packageName)
            put("oodles", versionName)
        }
    }

    fun getJson(): JSONObject {
        return JSONObject().apply {
            put("franz", 0L)
            put("biblical", 0L)
            put("hassle", 0L)
            put("sputnik", 0L)
            put("evzone", appInstallTime)
            put("cranston", 0L)
            put("rise", false)
        }
    }

    fun refreshHead(str: String) {
        runCatching {
            JSONObject(str).apply {
                val time = optString("writer_tt")
                splitTime(time)
                sweetIdStr = optString("charm_id")
                B1.c1(mHeadApp, optString("playful_fb_id"))
            }
        }
    }

    var time1 = 60000L
    var time2Per = 60000
    var timeWait = 60000

    private val mLimitHelper by lazy { LimitHelper() }

    var sweetIdStr = ""
    var isCanSisterRecord = true

    private fun splitTime(time: String) {
        if (time.contains("-")) {
            runCatching {
                val list = time.split("-")
                time1 = list[0].toInt() * 1000L
                time2Per = list[1].toInt() * 1000
                timeWait = list[2].toInt() * 1000
                mLimitHelper.showMaxHour = list[3].toInt()
                mLimitHelper.showMaxDay = list[4].toInt()
                mLimitHelper.clickMaxDay = list[5].toInt()
            }
        }
    }

    fun isSmartLimit(post: () -> Unit): Boolean {
        return mLimitHelper.isSmartLimit { post.invoke() }
    }


    var showTimeAd = 0L

    @JvmStatic
    fun showEvent() {
        showTimeAd = System.currentTimeMillis()
        mLimitHelper.showHourNumDay++
        mLimitHelper.showDayNumDay += addChar()
    }

    @JvmStatic
    fun clickAd() {
        mLimitHelper.clickDayNumDay++
    }

    private fun addChar(): Char {
        return ('o'..'z').random()
    }
}