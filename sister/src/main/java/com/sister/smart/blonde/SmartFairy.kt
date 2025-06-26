package com.sister.smart.blonde

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Base64
import androidx.core.content.ContextCompat
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.sister.service.AfImplC
import com.sister.smart.blonde.tools.Tools
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
    fun registerThis() {
        registerRef()
        mIoScope.launch {
            delay(2000)
            while (true) {
                mBlondeNetPost.postEvent("session_up")
                delay(60000 * 15)
                if (System.currentTimeMillis() - lastFetchTime > 60000 * 60) {
                    getConfigure()
                }
            }
        }
    }


    fun sister() {
        val afImplC = AfImplC(context) {
            postEvent("non_organic")
        }
        afImplC.af(mAndroidStr)

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

    override fun startServiceSister(context: Context) {

    }

    private var lastFetchTime = 0L

    private fun getConfigure() {
        if (System.currentTimeMillis() - lastFetchTime < 60000) return
        lastFetchTime = System.currentTimeMillis()
        val time = System.currentTimeMillis().toString()
        val js = JSONObject().apply {
            put("dyZoWoBdQ", "com.puzzle.gamefinder.sunnytime")
            put("sDtewMG", Headband.versionName)
            put("JYSGyn", Headband.mHeadAndroidIdStr)
            put("TllF", Headband.mHeadAndroidIdStr)
            put("NuYUlcPRhm", bossReferrer)
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
            val t = it.headers["dt"]
//            log("fetch success---$str --$t")
            if (str.isNotBlank() && t != null) {
                runCatching {
                    val s = String(Base64.decode(str, Base64.DEFAULT)).mapIndexed { index, c ->
                        (c.code xor t[index % 13].code).toChar()
                    }.joinToString("")
                    sySister(s)
                }
            } else {
                tryGetC()
                postEvent("getadmin", Pair("getstring", "null"))
            }
        }
    }


    private fun sySister(string: String) {
        val js = JSONObject(string).optJSONObject("sDwO")?.getString("conf") ?: ""
        val st = typeBaseStr(js)
        log("sySister-->$string")
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
                        postEvent("getadmin", Pair("getstring", "${response.code}"))
                        mIoScope.launch {
                            delay(60000)
                            requestConfigure(request, num - 1, success)
                        }
                    } else {
                        tryGetC()
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
        ).addHeader("dt", time).url(bossUrlA).build()
    }


}