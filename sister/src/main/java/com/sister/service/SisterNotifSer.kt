package com.sister.service

import android.app.Notification
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.sister.smart.blonde.R

/**
 * Date：2025/4/21
 * Describe:
 * com.sister.service.SisterNotifSer
 */
class SisterNotifSer : Service() {
    private var mNotification: Notification? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mNotification = getSisterNo(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        runCatching {
            startForeground(1101, mNotification)
        }
        return START_STICKY
    }

    private fun getSisterNo(context: Context): Notification {

        return NotificationCompat.Builder(context, "Sister_19").setAutoCancel(false)
            .setContentText("").setSmallIcon(R.drawable.ic_wor_girl).setOngoing(true)
            .setOnlyAlertOnce(true).setContentTitle("")
            .setCustomContentView(RemoteViews(context.packageName, R.layout.smart_blo_layout))
            .build()
    }

}