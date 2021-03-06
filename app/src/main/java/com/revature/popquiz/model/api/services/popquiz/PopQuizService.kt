package com.revature.popquiz.model.api.services.popquiz

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

/**
 * foreground service saved in the user settings somewhere,
 * the service should start a timer
 * foreground service
 * set up timer
 * then connect to actual popquiz
 */
@SuppressLint("UnspecifiedImmutableFlag")
fun setAlarm(context: Context) {
    /**
     * Allow users to set an alarm for a specific time in the day,
     * set multiple alarms at intervals? 30 minutes?
     */
//    val time = Calendar.getInstance().set(Calendar.HOUR_OF_DAY, 12)
//    val time = System.currentTimeInMillis() + 5000 //5 seconds
    val time = AlarmManager.INTERVAL_HALF_HOUR
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, PopQuizReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
}