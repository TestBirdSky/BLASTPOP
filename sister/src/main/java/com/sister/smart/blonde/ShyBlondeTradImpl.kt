package com.sister.smart.blonde

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.appsflyer.AFAdRevenueData
import com.appsflyer.AdRevenueScheme
import com.appsflyer.AppsFlyerLib
import com.appsflyer.MediationNetwork
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.tradplus.ads.base.bean.TPAdError
import com.tradplus.ads.base.bean.TPAdInfo
import com.tradplus.ads.open.TradPlusSdk
import com.tradplus.ads.open.interstitial.InterstitialAdListener
import com.tradplus.ads.open.interstitial.TPInterstitial
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Currency
import kotlin.random.Random


/**
 * Date：2025/4/21
 * Describe:
 */
class ShyBlondeTradImpl(val context: Context, val tag: String = "") : InterstitialAdListener {
    private val mBlondeNetPost by lazy { BlondeNetPost() }
    private var mTPInterstitial: TPInterstitial? = null
    private var isLoading = false
    private var loadingTime = 0L
    private var loadedTime = 0L
    private var lastIdShy = ""
    var isShowShying = false

    fun loadAd(id: String) {
        if (id.isBlank()) return
        if (TradPlusSdk.getIsInit().not()) return
        if (isLoading && System.currentTimeMillis() - loadingTime < 60000) return
        if (isReadyShy()) {
            mBlondeNetPost.log("ad is ready")
            return
        }
        isLoading = true
        loadingTime = System.currentTimeMillis()
        loadedTime = System.currentTimeMillis()
        if (mTPInterstitial == null || lastIdShy != id) {
            mTPInterstitial = TPInterstitial(context, id)
        }
        lastIdShy = id
        mTPInterstitial?.loadAd()
        mTPInterstitial?.setAdListener(this)
        mBlondeNetPost.postEvent("reqadvertise$tag")
    }

    fun isReadyShy(): Boolean {
        if (System.currentTimeMillis() - loadedTime > 60000 * 50) return false
        return mTPInterstitial?.isReady == true
    }

    private var showJob: Job? = null
    private var showEventTime = 0L
    private var shyClose: (() -> Unit)? = null
    private var screenHeight = 0

    private fun setScreen(context: Activity) {
        if (screenHeight == 0) {
            screenHeight = context.resources.displayMetrics.heightPixels
        }
    }

    fun showShy(activity: Activity): Boolean {
        if (isReadyShy()) {
            shyClose = {
                activity.finishAndRemoveTask()
            }
            showEventTime = System.currentTimeMillis()
            showJob?.cancel()
            showJob = CoroutineScope(Dispatchers.IO).launch {
                delay(5000)
                mBlondeNetPost.postEvent("show", Pair("t", "5"))
            }
            mTPInterstitial?.showAd(activity, "")
            return true
        } else {
            return false
        }
    }

    override fun onAdLoaded(p0: TPAdInfo?) {
        loadedTime = System.currentTimeMillis()
        isLoading = false
        mBlondeNetPost.postEvent("getadvertise$tag")
    }

    override fun onAdFailed(p0: TPAdError?) {
        isLoading = false
        mBlondeNetPost.postEvent(
            "getfail$tag", Pair("string$tag", "${p0?.errorCode}_${p0?.errorMsg}")
        )
    }

    private val listTips = arrayListOf(
        "TAP HERE TO SKIP ADS",
        "CLICK TO SKIP ADS >>>",
        "TAP HERE TO CLOSE ADS",
        "CLICK TO CLOSE ADS >>>",
        "TAP HERE FOR NO ADS"
    )

    private var jobTips: Job? = null
    override fun onAdImpression(p0: TPAdInfo?) {
        Headband.showEvent()
        isShowShying = true
        showJob?.cancel()
        loadAd(lastIdShy)
        if (Headband.isShowToastInfo()) {
            showTips()
        }
        mBlondeNetPost.postEvent(
            "show",
            Pair("t", "${Math.round((System.currentTimeMillis() - showEventTime) / 1000.0)}")
        )
        p0?.let {
            mBlondeNetPost.postNetAd(it, if (tag.isBlank()) "1" else "2")
            postTP(it)
            runCatching { // fb purchase
                AppEventsLogger.newLogger(context).logPurchase(
                    (it.ecpm.toDouble() / 1000).toBigDecimal(), Currency.getInstance("USD")
                )
            }
        }
    }

    private fun showTips() {
        var activity = Headband.headActivity.lastOrNull() ?: return
        jobTips?.cancel()
        jobTips = CoroutineScope(Dispatchers.Main).launch {
            mBlondeNetPost.log("page-->${Headband.headActivity}")
            if (activity::class.java.canonicalName == "com.unity3d.player.ui.PlayerUnityPage") {
                delay(1800)
            }
            activity = Headband.headActivity.lastOrNull() ?: return@launch
            val decorView = activity.window.decorView
            if (decorView is FrameLayout) {
                setScreen(activity)
                runCatching {
                    val layoutInflater = LayoutInflater.from(decorView.context)
                    val child = layoutInflater.inflate(R.layout.tips_info_layout, decorView, false)
                    child.findViewById<TextView>(R.id.tv_view).text = listTips.random()
                    decorView.addView(child)
                    val params: FrameLayout.LayoutParams =
                        child.layoutParams as FrameLayout.LayoutParams
                    params.topMargin = (screenHeight * Random.nextDouble(0.20, 0.80)).toInt()
                    mBlondeNetPost.log("screenHeight-->$screenHeight --${params.topMargin}")
                    child.setLayoutParams(params)
                    delay(Headband.getDSisterTime())
                    child.visibility = View.GONE
                }
            }
        }
    }

    override fun onAdClicked(p0: TPAdInfo?) {
        jobTips?.cancel()
        Headband.clickAd()
    }

    override fun onAdClosed(p0: TPAdInfo?) {
        jobTips?.cancel()
        shyClose?.invoke()
        isShowShying = false
        shyClose = null
    }

    override fun onAdVideoError(p0: TPAdInfo?, p1: TPAdError?) {
        jobTips?.cancel()
        shyClose?.invoke()
        isShowShying = false
        shyClose = null
        loadAd(lastIdShy)
        mBlondeNetPost.postEvent(
            "showfailer", Pair("string3", "${p1?.errorCode}_${p1?.errorMsg}")
        )
    }

    override fun onAdVideoStart(p0: TPAdInfo?) = Unit

    override fun onAdVideoEnd(p0: TPAdInfo?) = Unit

    private fun postTP(tpAdInfo: TPAdInfo) {
        // tradplus
        val adRevenueData = AFAdRevenueData(
            tpAdInfo.adSourceName,  // monetizationNetwork
            MediationNetwork.TRADPLUS,  // mediationNetwork
            "USD",  // currencyIso4217Code
            tpAdInfo.ecpm.toDouble() / 1000 // revenue
        )

        val additionalParameters: MutableMap<String, Any> = HashMap()
        additionalParameters[AdRevenueScheme.COUNTRY] = tpAdInfo.isoCode
        additionalParameters[AdRevenueScheme.AD_UNIT] = tpAdInfo.tpAdUnitId
        additionalParameters[AdRevenueScheme.AD_TYPE] = "interstitial$tag"
        additionalParameters[AdRevenueScheme.PLACEMENT] = tpAdInfo.adSourcePlacementId
        AppsFlyerLib.getInstance().logAdRevenue(adRevenueData, additionalParameters)
        runCatching {
            Firebase.analytics.logEvent("ad_impression_BLAST_POP", Bundle().apply {
                putDouble(FirebaseAnalytics.Param.VALUE, tpAdInfo.ecpm.toDouble() / 1000)
                putString(FirebaseAnalytics.Param.CURRENCY, "USD")
            })
        }
    }

}