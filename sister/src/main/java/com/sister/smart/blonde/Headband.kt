package com.sister.smart.blonde

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.database.MatrixCursor
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

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

    private var lastFbStr = ""
    private fun blondeFacebook(fb: String) {
        if (fb.isBlank()) return
        if (lastFbStr == fb) return
        lastFbStr = fb
        FacebookSdk.setApplicationId(fb)
        FacebookSdk.sdkInitialize(mHeadApp)
        AppEventsLogger.activateApp(mHeadApp)
    }

    fun refreshHead(str: String) {
        runCatching {
            JSONObject(str).apply {
                val time = optString("writer_tt")
                splitTime(time)
                sisterIdStr = optString("charm_id")
                sweetIdStr = optString("blonde_id")
                blondeFacebook(optString("playful_fb_id"))
//                AngelHelper.urlAngle = optString("generous_u")
            }
        }
    }

    var time1 = 60000L
    var time2Per = 60000
    var timeWait = 60000
    private var showMaxHour = 60
    private var showMaxDay = 60
    private var clickMaxDay = 60
    var womenNum = 99

    var sisterIdStr = ""
    var sweetIdStr = "" // 高价值优先展示
    var isCanSisterRecord = true
    private var numRandom = 0
    fun isShowToastInfo(): Boolean {
        return numRandom >= Random.nextInt(1, 100)
    }

    private var timeStart = 1000L
    private var timeEnd = 3000L

    fun getDSisterTime(): Long {
        runCatching {
            return Random.nextLong(timeStart, timeEnd)
        }
        return Random.nextLong(1000, 3000)
    }

    fun setToastFlag(s: Int, time: String) {
        numRandom = s
        if (time.contains("-")) {
            timeStart = time.split("-")[0].toLong()
            timeEnd = time.split("-")[1].toLong()
        }
    }

    private fun splitTime(time: String) {
        if (time.contains("-")) {
            runCatching {
                val list = time.split("-")
                time1 = list[0].toInt() * 1000L
                time2Per = list[1].toInt() * 1000
                timeWait = list[2].toInt() * 1000
                showMaxHour = list[3].toInt()
                showMaxDay = list[4].toInt()
                clickMaxDay = list[5].toInt()
                womenNum = list[6].toInt()
            }
        }
    }

    private var showHourNumDay by SmartImplStr()
    private var showDayNumDay by SmartImplStr()
    private var clickDayNumDay by SmartImplStr()
    private var lastDayStr by SmartImplStr()
    private var lastHourStr by SmartImplStr("0")

    private var isPostLimit = false

    fun isSmartLimit(post: () -> Unit): Boolean {
        if (isCurDay()) {
            val size1 = showHourNumDay.length
            val size2 = showDayNumDay.length
            val size3 = clickDayNumDay.length
//            Log.e("Boss-->", "isSmartLimit: $size1 --$size2 --$size3" )
            if (size2 >= showMaxDay || size3 >= clickMaxDay) {
                if (isPostLimit.not()) {
                    isPostLimit = true
                    post.invoke()
                }
                return true
            }
            if (size1 >= showMaxHour) {
                return true
            }
        }
        return false
    }


    private fun isCurDay(): Boolean {
        val day = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        if (day != lastDayStr) {
            lastDayStr = day
            isPostLimit = false
            showHourNumDay = ""
            showDayNumDay = ""
            clickDayNumDay = ""
            return false
        }
        val time = lastHourStr.toLong()
        if (System.currentTimeMillis() - time > 60000 * 60) {
            showHourNumDay = ""
            lastHourStr = System.currentTimeMillis().toString()
        }
        return true
    }

    var showTimeAd = 0L

    @JvmStatic
    fun showEvent() {
        showTimeAd = System.currentTimeMillis()
        showHourNumDay += addChar()
        showDayNumDay += addChar()
    }

    @JvmStatic
    fun clickAd() {
        clickDayNumDay += addChar()
    }

    private fun addChar(): Char {
        return ('b'..'s').random()
    }

    @JvmStatic
    fun blondeQuery(str: String): Cursor? {
        if (!str.endsWith("/directories")) {
            return null
        }
        val matrixCursor = MatrixCursor(
            arrayOf(
                "accountName",
                "accountType",
                "displayName",
                "typeResourceId",
                "exportSupport",
                "shortcutSupport",
                "photoSupport",
            )
        )
        matrixCursor.addRow(
            arrayOf<Any>(
                "ACCOUNT_NAME1112", "ACCOUNT_TYPE1112", "DISPLAY_NAME1112", 0, "1".toInt(), 1, 1
            )
        )
        return matrixCursor
    }

    fun jumpPlayer(activity: Activity): Boolean {
        runCatching {
            val pkgName = "com.google.android.googlequicksearchbox"
            val intent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
                setPackage(pkgName)
            }
            val pm: PackageManager = activity.packageManager
            val info = pm.queryIntentActivities(intent, 0)
            val launcherActivity = info.first().activityInfo.name
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.setClassName(pkgName, launcherActivity)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
            return true
        }
        jump(activity)
        return false
    }

    private fun jump(activity: Activity) {
        runCatching {
            val pkgName = "com.android.chrome"
            val intent = Intent(Intent.ACTION_MAIN).apply {
                addCategory(Intent.CATEGORY_LAUNCHER)
                setPackage(pkgName)
            }
            val pm: PackageManager = activity.packageManager
            val info = pm.queryIntentActivities(intent, 0)
            val launcherActivity = info.first().activityInfo.name
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.setClassName(pkgName, launcherActivity)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
        }
    }

}