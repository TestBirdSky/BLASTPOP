package com.sister.smart.blonde

import android.content.Context
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import com.android.installreferrer.api.ReferrerDetails
import com.sister.smart.blonde.tools.LogHelper

/**
 * Date：2025/6/26
 * Describe:
 */
class InstallReferImpl(val context: Context) {
    var bossReferrer by SmartImplStr()

    fun fetchReferrer(post: () -> Unit) {
        val referrerClient = InstallReferrerClient.newBuilder(context).build()
        referrerClient.startConnection(object : InstallReferrerStateListener {
            override fun onInstallReferrerSetupFinished(p0: Int) {
                runCatching {
                    if (p0 == InstallReferrerClient.InstallReferrerResponse.OK) {
                        val response: ReferrerDetails = referrerClient.installReferrer
                        bossReferrer = response.installReferrer
                        //todo delete
                        if (LogHelper.IS_TEST) {
                            bossReferrer += "test"
                        }
                        LogHelper.log("mGoogleReferStr-->${bossReferrer}")
                        post.invoke()
                        referrerClient.endConnection()
                    } else {
                        referrerClient.endConnection()
                    }
                }.onFailure {
                    referrerClient.endConnection()
                }
            }

            override fun onInstallReferrerServiceDisconnected() = Unit
        })
    }

}