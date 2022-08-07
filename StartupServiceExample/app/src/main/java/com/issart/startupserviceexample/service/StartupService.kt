package com.issart.startupserviceexample.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.issart.startupserviceexample.R

class StartupService : Service() {

    private var isStarted = false
    private var manager: NotificationManager? = null

    override fun onBind(p0: Intent?): IBinder? = ServiceBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isStarted) {
            val notifications = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(getString(R.string.app_name))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setOngoing(true)
                .build()
            Log.d(SERVICE_TAG, "Starting service")

            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            createNotificationChannel(CHANNEL_ID, CHANNEL_NAME)

            startForeground(NOTIFICATION_ID, notifications)
            isStarted = true
        }
        return START_STICKY
    }

    override fun onDestroy() {
        stopForeground(true)
        isStarted = false
        super.onDestroy()
    }

    private fun createNotificationChannel(channelId: String, channelName: String): String {
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        channel.lightColor = Color.BLUE
        channel.lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
        manager?.createNotificationChannel(channel)
        return channelId
    }

    inner class ServiceBinder : Binder() {
        fun getService(): StartupService {
            return this@StartupService
        }
    }

    companion object {
        const val NOTIFICATION_ID = 2021
        const val CHANNEL_ID = "34"
        const val CHANNEL_NAME = "Connective"
        const val SERVICE_TAG = "StartupService"
    }
}