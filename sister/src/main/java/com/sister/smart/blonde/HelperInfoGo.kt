package com.sister.smart.blonde

import android.accounts.Account
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import com.sister.blo.SerHelper

/**
 * Date：2025/6/27
 * Describe:
 */
class HelperInfoGo {
    // SerHelper.ew()
    private val infoStr = "com.flutter.ii19.pro"
    private val mBundleHelper by lazy { BundleHelper() }
    fun c(account: Account) {
        ContentResolver.setSyncAutomatically(account, infoStr, true)
        ContentResolver.addPeriodicSync(account, mBundleHelper.ewStr, Bundle(), 1)
        val bundle = mBundleHelper.getBundle2()
        ContentResolver.requestSync(account, mBundleHelper.ewStr, bundle)
    }

}