package com.issart.startupserviceexample.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.issart.startupserviceexample.service.StartupService
import androidx.core.content.ContextCompat.startForegroundService

class BootReceiver: BroadcastReceiver() {
    override fun onReceive(receiverContext: Context, receiverIntent: Intent) {
        Log.d(StartupService.SERVICE_TAG, "Action is : ${receiverIntent.action}")
        if(receiverIntent.action == Intent.ACTION_BOOT_COMPLETED
            || receiverIntent.action == Intent.ACTION_REBOOT) {
            val intent = Intent(receiverContext, StartupService::class.java)
            Log.d(StartupService.SERVICE_TAG, "Starting service")
            startForegroundService(receiverContext, intent)
        }
    }
}