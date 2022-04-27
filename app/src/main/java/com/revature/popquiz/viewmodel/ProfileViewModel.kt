package com.revature.popquiz.viewmodel

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.revature.popquiz.service.AlarmReceiver
import com.revature.popquiz.service.INTENT_COMMAND
import com.revature.popquiz.service.INTENT_COMMAND_POPQUIZ
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor():ViewModel(), LifecycleObserver {


    fun setupAlarm(context: Context){

        var waitTime:Long = 60_000* 1
        var startTime = System.currentTimeMillis()
        startTime += waitTime

        val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager

        val popIntent = Intent(context, AlarmReceiver::class.java)
        popIntent.putExtra(INTENT_COMMAND, INTENT_COMMAND_POPQUIZ)
        val pendingPop =
            PendingIntent.getBroadcast(context,0,popIntent,0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            startTime,
            waitTime,
            pendingPop
        )
    }
    fun stopAlarm(context: Context){

        val alarmManager = context.getSystemService(ComponentActivity.ALARM_SERVICE) as AlarmManager

        val popIntent = Intent(context, AlarmReceiver::class.java)
        popIntent.putExtra(INTENT_COMMAND, INTENT_COMMAND_POPQUIZ)
        val pendingPop =
            PendingIntent.getBroadcast(context,0,popIntent,0)
        alarmManager.cancel(pendingPop)
    }
}