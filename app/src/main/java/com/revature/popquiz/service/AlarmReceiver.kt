package com.revature.popquiz.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.revature.popquiz.MainActivity
import com.revature.popquiz.R

/**
 * Receive intent in MainActivity
 * if intent is there, go to quiz screen with
 */

class AlarmReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val command = intent?.getStringExtra(INTENT_COMMAND)
        if(command == INTENT_COMMAND_POPQUIZ){
            if(context != null)
            sendNotification(context)
        }
    }
    fun sendNotification(context: Context){
        //if were on a new build version, create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //create the channel
            val serviceChannel = NotificationChannel(
                POP_QUIZ_NOTIFICATION_CHANNEL,
                "Pop!Quiz Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            //get a copy of the notification manager
            val notificationManager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            //add our channel
            notificationManager.createNotificationChannel(serviceChannel)
        }
        val notificationIntent =
            Intent(context, MainActivity::class.java)
        notificationIntent.putExtra(INTENT_POPQUIZ_ID, INTENT_NOTIFICATION_ID)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, notificationIntent, 0)

//        val stopAlarmIntent =
//            Intent(context,MainActivity::class.java)
//        stopAlarmIntent.putExtra(INTENT_COMMAND, INTENT_COMMAND_STOP)
//        val pendingStopAlarmIntent =
//            PendingIntent.getBroadcast(
//                context,
//                0,
//                stopAlarmIntent,
//                0
//            )


        val popNotification = NotificationCompat.Builder(
            context, POP_QUIZ_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Pop! Quiz")
            .setContentText("It's time for a pop quiz!")
//            .addAction(0,"Stop Pop!Quiz alarm",pendingStopAlarmIntent)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(INTENT_NOTIFICATION_ID, popNotification.build())
    }


}