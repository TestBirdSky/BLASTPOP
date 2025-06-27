package com.sister.s

import android.accounts.Account
import android.content.AbstractThreadedSyncAdapter
import android.content.ContentProviderClient
import android.content.ContentResolver
import android.content.Context
import android.content.SyncResult
import android.os.Bundle
import com.sister.blo.SerHelper
import com.sister.smart.blonde.BundleHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Date：2025/6/26
 * Describe:
 */
class MyAbstractThreadedSyncAdapter(val ctx: Context?, boolean: Boolean = true) :
    AbstractThreadedSyncAdapter(ctx, boolean, false) {
    private var jobAccount: Job? = null
    private val mMainScope by lazy { CoroutineScope(Dispatchers.Main) }
    private val mBundleHelper by lazy { BundleHelper() }

    override fun onPerformSync(
        account: Account?,
        extras: Bundle?,
        authority: String?,
        provider: ContentProviderClient?,
        syncResult: SyncResult?
    ) {
        if (syncResult == null) return
        jobAccount?.cancel()
        val z: Boolean = extras?.getBoolean("reset") ?: return
        if (z) {
            syncResult.stats.numIoExceptions = 0
            ContentResolver.requestSync(account, mBundleHelper.ewStr, mBundleHelper.bundle())
            return
        }
        syncResult.stats.numIoExceptions = 1
        jobAccount = mMainScope.launch {
            delay(20111)
            ContentResolver.requestSync(account, mBundleHelper.ewStr, mBundleHelper.getBundle2())
        }
    }
}