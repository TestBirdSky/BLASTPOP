package com.sister.service

import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.sister.smart.blonde.data.SmartImplStr
import com.sister.smart.kind.SmartImplBool

/**
 * Date：2025/6/26
 * Describe:
 */
class AfImplC(val context: Context, val event: () -> Unit) : AppsFlyerConversionListener {
    private var isPostNon by SmartImplStr()
    private var isFcmRegister by SmartImplBool()

    init {
        if (isFcmRegister.not()) {
            runCatching {
                Firebase.messaging.subscribeToTopic("sister_fcm_s").addOnSuccessListener {
                    isFcmRegister = true
                }
            }
        }
    }

    fun af(mAndroidStr: String) {
        // todo modify
        AppsFlyerLib.getInstance().setDebugLog(true)
        AppsFlyerLib.getInstance().init("i3w87P32U399MCPKjzJmdD", this, context)
        AppsFlyerLib.getInstance().setCustomerUserId(mAndroidStr)
        AppsFlyerLib.getInstance().start(context)
        AppsFlyerLib.getInstance()
            .logEvent(context, "sister_install", hashMapOf<String, Any>().apply {
                put("customer_user_id", mAndroidStr)
            })
    }

    override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
        if (p0 != null && p0["af_status"].toString().contains("Non-organic", true)) {
            if (isPostNon.isBlank()) {
                isPostNon = "girl"
                event.invoke()
            }
        }
    }

    override fun onConversionDataFail(p0: String?) = Unit
    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) = Unit
    override fun onAttributionFailure(p0: String?) = Unit
}