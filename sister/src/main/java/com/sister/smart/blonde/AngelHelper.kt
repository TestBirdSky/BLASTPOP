package com.sister.smart.blonde

import android.app.Application
import android.content.Context
import android.provider.Settings
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.sister.s.SisterWorker
import com.sister.smart.blonde.c.SmartLifecycleImpl
import com.sister.smart.kind.HelperApp
import java.util.UUID
import java.util.concurrent.TimeUnit

/**
 * Date：2025/4/21
 * Describe:
 * com.sister.smart.blonde.AngelHelper
 */
object AngelHelper {

    @JvmStatic
    fun sisterInit(context: Context) {
        HelperApp.mApp = context as Application
        if (Headband.mHeadAndroidIdStr.isBlank()) {
            Headband.mHeadAndroidIdStr = UUID.randomUUID().toString()
        }
        HelperApp.mCallAction.a()
        context.packageManager.getPackageInfo(context.packageName, 0).apply {
            Headband.versionName = versionName
            Headband.appInstallTime = firstInstallTime
        }
        angelStr(context)
    }

    @JvmStatic
    fun angelStr(context: Context) {
        val mSmartFairy = SmartFairy(context)
        mSmartFairy.sister()
        mSmartFairy.registerThis()
        if (context is Application) {
            context.registerActivityLifecycleCallbacks(SmartLifecycleImpl())
        }
    }

}