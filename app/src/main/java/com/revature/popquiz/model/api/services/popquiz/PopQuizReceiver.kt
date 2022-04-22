package com.revature.popquiz.model.api.services.popquiz

import android.app.NotificationChannel
import android.app.NotificationManager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.revature.popquiz.R

class PopQuizReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            if (context != null) {
                "Pop! Quiz".showNotification(context)
            }
        } catch (e: Exception) {
            Log.d("Receive Exception", e.printStackTrace().toString())
        }
    }

    private fun String.showNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "CHANNEL_ID"
        val channelId = "MESSAGE_ID"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(this)
            .setContentText(this)
            .setSmallIcon(R.drawable.rev_logo_2)
        manager.notify(1, builder.build())
    }

//    private fun createNotificationChannel(context: Context) {
//
//        //Create the NotificationChannel
//        //This is only available in SDK Version 26+
//        /**
//         * Higher notification importance: shows everywhere, makes noise and peeks. May use full screen intents.
//         */
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val notificationChannel = "NotificationChannelName"
//            val descriptionText = "NotificationChannelDescriptionText"
//            val importance = NotificationManager.IMPORTANCE_HIGH
//            val channel = NotificationChannel("CHANNEL_ID", notificationChannel, importance).apply {
//                description = descriptionText
//            }
//            //Register the channel with the system
//            val notificationManager: NotificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
}