package com.sister.smart.blonde

import android.app.Activity
import android.content.Context
import com.tradplus.ads.open.TradPlusSdk

/**
 * Date：2025/4/21
 * Describe:
 */
class BlondeAdHelper(val context: Context) {

    private val mShyBlondeTradImpl1 by lazy { ShyBlondeTradImpl(context, "") }

    private val mShyBlondeTradImpl2 by lazy { ShyBlondeTradImpl(context, "1") }

    fun initBlonde() {
        // todo modify
        TradPlusSdk.initSdk(context, "114FE8DB631B3389BDDDD15D81E45E39")
    }

    fun load() {
        mShyBlondeTradImpl1.loadAd(Headband.sweetIdStr)
        mShyBlondeTradImpl2.loadAd(Headband.sisterIdStr)
    }

    fun isReady(): String {
        if (mShyBlondeTradImpl1.isReadyShy()) {
            return "1"
        }
        if (mShyBlondeTradImpl2.isReadyShy()) {
            return "2"
        }
        return "false"
    }

    fun isShowingAd(): Boolean {
        if (mShyBlondeTradImpl1.isShowShying) return true
        if (mShyBlondeTradImpl2.isShowShying) return true
        return false
    }

    fun showPage(activity: Activity) {
        if (mShyBlondeTradImpl1.showShy(activity)) {
            return
        }
        if (mShyBlondeTradImpl2.showShy(activity)) {
            return
        }
        activity.finish()
        load()
    }

}