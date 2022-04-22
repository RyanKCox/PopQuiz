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
     * CURRENTLY this will set an alarm for 5000 milliseconds in the future.
     */
//    val time = Calendar.getInstance().set(Calendar.HOUR_OF_DAY, 12)
    val time = System.currentTimeMillis() + 5000
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, PopQuizReceiver::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent)
}