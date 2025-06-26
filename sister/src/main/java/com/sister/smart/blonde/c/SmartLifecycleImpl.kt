package com.sister.smart.blonde.c

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.sister.smart.blonde.BlondeNetPost
import com.sister.smart.blonde.Headband
import com.sister.smart.blonde.ad.ActivityHelper
import com.sister.smart.blonde.tools.LogHelper
import com.sister.smart.blonde.tools.Tools
import com.sister.smart.kind.HelperApp

/**
 * Date：2025/6/26
 * Describe:
 */
class SmartLifecycleImpl : Application.ActivityLifecycleCallbacks {
    private val mActivityHelper = ActivityHelper()
    private val mBlondeNetPost by lazy { BlondeNetPost() }
    private var dancerNum = 0
    companion object {
        var type = ""
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        log("onActivityCreated->$activity")
        addActivity(activity)
        val isP = HelperApp.eventKind(activity, mBlondeNetPost)
        mActivityHelper.action(isP, activity, mBlondeNetPost) {
            HelperApp.mCallAction.mBadAdHelper.showPage(activity)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        dancerNum++
        Tools.startService(activity)

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
            mActivityHelper.isFront = false
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

    private fun log(msg: String) {
        LogHelper.log(msg)
    }

    private fun addActivity(activity: Activity) {
        Headband.headActivity.add(activity)
    }

    private fun removePage(activity: Activity) {
        Headband.headActivity.remove(activity)
    }
}