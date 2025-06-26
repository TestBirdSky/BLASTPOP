package com.sister.smart.blonde

import android.app.Activity
import android.content.Context
import com.bytedance.sdk.openadsdk.api.init.PAGSdk
import com.bytedance.sdk.openadsdk.api.interstitial.PAGInterstitialAd
import com.bytedance.sdk.openadsdk.api.interstitial.PAGInterstitialAdInteractionCallback
import com.bytedance.sdk.openadsdk.api.interstitial.PAGInterstitialAdLoadCallback
import com.bytedance.sdk.openadsdk.api.interstitial.PAGInterstitialRequest
import com.bytedance.sdk.openadsdk.api.model.PAGAdEcpmInfo
import com.bytedance.sdk.openadsdk.api.model.PAGErrorModel
import com.facebook.appevents.AppEventsLogger
import com.sister.smart.blonde.ad.PangleHelper
import com.sister.smart.blonde.tools.LogHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Currency


/**
 * Date：2025/6/26
 * Describe:
 */
class PangleSmart(val context: Context, val tag: String = "") {
    private val mPangleHelper by lazy { PangleHelper(tag) }
    private var mPAGInterstitialAd: PAGInterstitialAd? = null
    var isShowShying = false
    fun load(id: String) {
        if (id.isBlank()) return
        if (PAGSdk.isInitSuccess().not()) return
        if (mPangleHelper.isCanLoadPangle().not()) return
        if (isReadyAd()) {
            LogHelper.log("pangle is ready")
            return
        }
        mPangleHelper.startLoad()
        PAGInterstitialAd.loadAd(
            id,
            PAGInterstitialRequest(context),
            object : PAGInterstitialAdLoadCallback {
                override fun onError(pagErrorModel: PAGErrorModel) {
                    mPangleHelper.loadFinish("${pagErrorModel.errorCode}_${pagErrorModel.errorMessage}")
                }

                override fun onAdLoaded(pagInterstitialAd: PAGInterstitialAd) {
                    mPangleHelper.loadFinish("")
                    mPAGInterstitialAd = pagInterstitialAd
                }
            })
    }

    fun isReadyAd(): Boolean {
        if (mPangleHelper.isTimeout()) {
            mPAGInterstitialAd = null
            return false
        }
        return mPAGInterstitialAd?.isReady == true
    }

    private var showJob: Job? = null

    fun showAd(activity: Activity): Boolean {
        val ad = mPAGInterstitialAd
        if (ad != null && isReadyAd()) {
            showJob?.cancel()
            showJob = CoroutineScope(Dispatchers.IO).launch {
                delay(5000)
                mPangleHelper.mBlondeNetPost.postEvent("show", Pair("t", "5"))
                activity.finishAndRemoveTask()
            }
            val timeShow = System.currentTimeMillis()
            ad.setAdInteractionCallback(object : PAGInterstitialAdInteractionCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    Headband.clickAd()
                }

                override fun onAdDismissed() {
                    super.onAdDismissed()
                    isShowShying = false
                    activity.finishAndRemoveTask()
                }

                override fun onAdShowFailed(pagErrorModel: PAGErrorModel) {
                    super.onAdShowFailed(pagErrorModel)
                    activity.finishAndRemoveTask()
                    isShowShying = false
                    mPangleHelper.mBlondeNetPost.postEvent(
                        "showfailer", Pair(
                            "string3", "${pagErrorModel.errorCode}_${pagErrorModel.errorMessage}"
                        )
                    )
                }

                override fun onAdReturnRevenue(pagAdEcpmInfo: PAGAdEcpmInfo?) {
                    super.onAdReturnRevenue(pagAdEcpmInfo)
                    Headband.showEvent()
                    showJob?.cancel()
                    isShowShying = true
                    mPangleHelper.mBlondeNetPost.postEvent("show", Pair("t", "${Math.round((System.currentTimeMillis() - timeShow) / 1000.0)}"))
                    pagAdEcpmInfo?.let {
                        mPangleHelper.postAd(it)
                        runCatching { // fb purchase
                            AppEventsLogger.newLogger(context).logPurchase(
                                (it.cpm.toDouble() / 1000).toBigDecimal(),
                                Currency.getInstance("USD")
                            )
                        }
                    }
                }
            })
            ad.show(activity)
            mPAGInterstitialAd = null
        }
        return false
    }

}