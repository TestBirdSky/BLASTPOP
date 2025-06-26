package com.sister.smart.blonde

import com.tradplus.ads.base.bean.TPAdInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
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

/**
 * Date：2025/4/21
 * Describe:
 */
class BlondeNetPost : BaseBossInfo() {
    private val mIoScope by lazy { CoroutineScope(Dispatchers.IO + SupervisorJob()) }
    private val okHttpClient = OkHttpClient()
    private var refSisterStatus by SmartImplStr()
    private val jsInstall by lazy {
        JSONObject(Headband.getJson().toString()).apply {
            put("lexicon", "")
            put("canton", "")
            put("won", "andover")
        }
    }

    fun postJSAd(jsonObject: JSONObject) {
        val js = commonJSONSister().apply {
            put("rouge", jsonObject)
        }.toString()
        blondeNet(sisterReq(js), 2)
    }

    fun postNetAd(tp: TPAdInfo, tag: String) {
        postJSAd(JSONObject().apply {
            put("consider", tp.ecpm.toDouble() * 1000)
            put("splash", "USD")
            put("woodshed", tp.adSourceName)
            put("swept", "tradplus")
            put("sternum", tp.adSourcePlacementId)
            put("seaside", "interstitial$tag")
            put("torn", tp.format)
        })
    }

    fun postIns(refStr: String) {
        if (refSisterStatus.isNotBlank()) return
        val js = commonJSONSister().apply {
            put("vestal", jsInstall.apply {
                put("astoria", "")
                put("sri", refStr)
            })
        }.toString()
        blondeNet(sisterReq(js), 31, success = {
            refSisterStatus = "girl"
        })

    }

    private val listName = arrayListOf("getadmin", "first_start", "non_organic")
    private fun isCanPostEvent(name: String): Boolean {
        if (Headband.isCanSisterRecord) return true
        if (listName.contains(name)) return true
        return false
    }

    fun postEvent(name: String, pair: Pair<String, String>? = null) {
        if (isCanPostEvent(name).not()) {
            log("cancel post $name")
            return
        }
        log(" post $name --$pair")
        val js = commonJSONSister().apply {
            put("bantam", name)
            pair?.let {
                put("pinscher", JSONObject().apply {
                    put(it.first, it.second)
                })
            }
        }.toString()
        blondeNet(sisterReq(js), 5)
    }

    private fun blondeNet(request: Request, tryNum: Int = 3, success: () -> Unit = {}) {
        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                log("--<$e")
                if (tryNum > 0) {
                    mIoScope.launch {
                        delay(22000)
                        blondeNet(request, tryNum - 1, success)
                    }
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string() ?: ""
                log("body--->$body")
                if (response.code == 200) {
                    success.invoke()
                } else {
                    if (tryNum > 0) {
                        mIoScope.launch {
                            delay(22000)
                            blondeNet(request, tryNum - 1, success)
                        }
                    }
                }
            }
        })
    }

    private fun sisterReq(body: String): Request {
        return Request.Builder().post(
            body.toRequestBody("application/json".toMediaType())
        ).url(bossUrl).build()
    }

}