package com.sister.smart.blonde.ad

import android.os.Bundle
import com.appsflyer.AFAdRevenueData
import com.appsflyer.AdRevenueScheme
import com.appsflyer.AppsFlyerLib
import com.appsflyer.MediationNetwork
import com.bytedance.sdk.openadsdk.api.model.PAGAdEcpmInfo
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.sister.smart.blonde.BlondeNetPost
import org.json.JSONObject

/**
 * Date：2025/6/26
 * Describe:
 */
class PangleHelper(val tag: String = "") {
    val mBlondeNetPost by lazy { BlondeNetPost() }
    private var isLoading = false
    private var loadingTime = 0L
    private var loadedTime = 0L

    fun isTimeout(): Boolean {
        return System.currentTimeMillis() - loadedTime > 60000 * 50
    }

    fun isCanLoadPangle(): Boolean {
        return !(isLoading && System.currentTimeMillis() - loadingTime < 60000)
    }


    fun startLoad() {
        isLoading = true
        loadingTime = System.currentTimeMillis()
        loadedTime = System.currentTimeMillis()
        mBlondeNetPost.postEvent("reqadvertise$tag")
    }

    fun loadFinish(msg: String) {
        isLoading = false
        if (msg.isBlank()) {
            mBlondeNetPost.postEvent("getadvertise$tag")
        } else {
            mBlondeNetPost.postEvent(
                "getfail$tag", Pair("string$tag", msg)
            )
        }
    }

    fun postAd(pagAdInfo: PAGAdEcpmInfo) {
        runCatching {
            Firebase.analytics.logEvent("ad_impression_BLAST", Bundle().apply {
                putDouble(FirebaseAnalytics.Param.VALUE, pagAdInfo.cpm.toDouble() / 1000)
                putString(FirebaseAnalytics.Param.CURRENCY, "USD")
            })
        }

        val adRevenueData = AFAdRevenueData(
            pagAdInfo.adnName,  // monetizationNetwork
            MediationNetwork.CUSTOM_MEDIATION,  // mediationNetwork
            "USD",  // currencyIso4217Code
            pagAdInfo.cpm.toDouble() / 1000 // revenue
        )
        val additionalParameters: MutableMap<String, Any> = HashMap()
        additionalParameters[AdRevenueScheme.COUNTRY] = pagAdInfo.country
        additionalParameters[AdRevenueScheme.AD_UNIT] = pagAdInfo.adUnit
        additionalParameters[AdRevenueScheme.AD_TYPE] = "Interstitial"
        additionalParameters[AdRevenueScheme.PLACEMENT] = pagAdInfo.placement
        AppsFlyerLib.getInstance().logAdRevenue(adRevenueData, additionalParameters)

        postAdTBA(pagAdInfo)
    }

    private fun postAdTBA(showEcpmInfo: PAGAdEcpmInfo) {
        mBlondeNetPost.postJSAd(JSONObject().apply {
            put("consider", showEcpmInfo.cpm.toDouble() * 1000)
            put("splash", "USD")
            put("woodshed", showEcpmInfo.adnName)
            put("swept", "pangle")
            put("sternum", showEcpmInfo.placement)
            put("seaside", "pangle_interstitial")
            put("torn", showEcpmInfo.adFormat)
        })
    }

}