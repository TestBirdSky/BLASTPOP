package com.sister.smart.blonde

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat

/**
 * Date：2025/4/21
 * Describe:
 */
class SisterNoService : Service() {
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
            startForeground(1001, mNotification)
        }
        return START_STICKY
    }

    private fun getSisterNo(context: Context): Notification {
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(
                "Notification", "Notification Channel", NotificationManager.IMPORTANCE_DEFAULT
            )
            (context.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
                channel
            )
        }
        val noti: Notification =
            NotificationCompat.Builder(context, "Notification").setAutoCancel(false)
                .setContentText("").setSmallIcon(R.drawable.ic_wor_girl).setOngoing(true)
                .setOnlyAlertOnce(true).setContentTitle("")
                .setCustomContentView(RemoteViews(context.packageName, R.layout.smart_blo_layout))
                .build()
        return noti
    }

}