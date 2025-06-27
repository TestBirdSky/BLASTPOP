package com.sister.smart.kind

import android.app.Activity
import android.content.Context
import com.bytedance.sdk.openadsdk.api.init.PAGConfig
import com.bytedance.sdk.openadsdk.api.init.PAGSdk
import com.bytedance.sdk.openadsdk.api.init.PAGSdk.PAGInitCallback
import com.sister.smart.blonde.Headband
import com.sister.smart.blonde.PangleSmart
import com.sister.smart.blonde.R
import com.sister.smart.blonde.ad.ShyBlondeTradImpl
import com.sister.smart.blonde.tools.LogHelper
import com.tradplus.ads.open.TradPlusSdk
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Date：2025/4/21
 * Describe:
 */
class BlondeAdHelper(val context: Context) : PAGInitCallback {

    private val mShyBlondeTradImpl1 by lazy { ShyBlondeTradImpl(context, "") }
    private val mPangleSmart by lazy { PangleSmart(context, "1") }

    fun initBlonde() {
        // todo
        TradPlusSdk.initSdk(context, "114FE8DB631B3389BDDDD15D81E45E39")
        initPangle()
    }

    private fun initPangle() {
        // todo modify
        val pAGInitConfig = PAGConfig.Builder().appId("8580262").appIcon(R.mipmap.ic_pangle_l)
            // todo del
            .debugLog(true).build()
        PAGSdk.init(context, pAGInitConfig, this)
    }

    fun load() {
        if (LogHelper.smartISPangle) {
            mPangleSmart.load(Headband.sweetIdStr)
        } else {
            mShyBlondeTradImpl1.loadAd(Headband.sweetIdStr)
        }
    }

    fun isReady(): String {
        if (LogHelper.smartISPangle) {
            if (mPangleSmart.isReadyAd()) {
                return "2"
            }
        } else {
            if (mShyBlondeTradImpl1.isReadyShy()) {
                return "1"
            }
        }
        return "false"
    }

    fun isShowingAd(): Boolean {
        if (LogHelper.smartISPangle) {
            return mPangleSmart.isShowShying
        } else {
            if (mShyBlondeTradImpl1.isShowShying) return true
        }
        return false
    }

    fun showPage(activity: Activity) {
        if (LogHelper.smartISPangle) {
            if (mPangleSmart.showAd(activity)) {
                return
            }
        } else {
            if (mShyBlondeTradImpl1.showShy(activity)) {
                return
            }
        }
        activity.finish()
        load()
    }

    override fun success() {}

    override fun fail(p0: Int, p1: String?) {
        CoroutineScope(Dispatchers.IO).launch {
            delay(40000)
            initPangle()
        }
    }

}