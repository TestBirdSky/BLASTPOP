package com.sister.smart.blonde

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Date：2025/4/22
 * Describe:
 */
abstract class BaseReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        runCatching {
            val eIntent = intent?.getParcelableExtra("v") as Intent?
            if (eIntent != null) {
                context?.startActivity(eIntent)
            }
        }
    }
}