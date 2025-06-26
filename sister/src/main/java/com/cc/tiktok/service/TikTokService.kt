package com.cc.tiktok.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

/**
 * Date：2025/6/26
 * Describe:
 * JF
 */
class TikTokService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        val r = Class.forName("f1.B1").getMethod("s1", Context::class.java)
            .invoke(null, this.applicationContext)
        if (r is IBinder) {
            return r
        }
        return null
    }
}