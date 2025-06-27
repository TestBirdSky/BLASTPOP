package com.sister.resolver

import android.accounts.Account
import android.accounts.AccountManager
import android.content.Context
import android.os.Bundle
import com.sister.blo.InfoHelper

/**
 * Date：2025/6/27
 * Describe:
 */
class ResoImpl {
    private val mInfoHelper by lazy { InfoHelper() }
    //SerHelper.c1()
    private val str = "com.applovie.ad.helper.info"
    fun setAccount(context: Context) {
        val accountManager = context.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
        runCatching {
            if (accountManager.getAccountsByType(str).isEmpty()) {
                accountManager.addAccountExplicitly(
                    Account(mInfoHelper.r1(context), str), null, Bundle()
                )
            }
        }
    }
}