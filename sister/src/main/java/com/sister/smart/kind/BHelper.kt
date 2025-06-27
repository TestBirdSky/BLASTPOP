package com.sister.smart.kind

import android.accounts.Account
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import com.sister.blo.SerHelper
import com.sister.smart.blonde.HelperInfoGo

/**
 * Date：2025/6/27
 * Describe:
 */
class BHelper {

    private val mAccountInfo by lazy { AccountInfo() }
    private val mHelperInfoGo by lazy { HelperInfoGo() }

    fun h(context: Context) {
        val account = mAccountInfo.ai(context)
        mHelperInfoGo.c(account)
    }
}