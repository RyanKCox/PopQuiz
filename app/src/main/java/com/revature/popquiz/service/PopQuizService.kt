package com.revature.popquiz.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.revature.popquiz.MainActivity
import com.revature.popquiz.R
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*


const val POP_QUIZ_NOTIFICATION_CHANNEL = "Pop!Quiz"
const val INTENT_COMMAND = "Command"
const val INTENT_COMMAND_POPQUIZ = "PopQuiz"
const val INTENT_COMMAND_STOP = "Stop"
const val INTENT_COMMAND_START = "Start"
const val INTENT_POPQUIZ_ID = "ID"
const val INTENT_NOTIFICATION_ID = 1

class PopQuizService: Service() {

    //default timer
    var waitTime:Long = 30000 //60_000* 1

    override fun onBind(p0: Intent?): IBinder? =null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val command = intent?.getStringExtra(INTENT_COMMAND)

        //Stop service if command stop received
        if (command == INTENT_COMMAND_STOP) {
            stopService()
            return START_NOT_STICKY
        }
        else if ( command == INTENT_COMMAND_POPQUIZ ){

            //Quiz intent
            takeQuizNotification()
            return START_NOT_STICKY
        }

        //Launch notification
        createRunningNotification()

        return START_NOT_STICKY

    }
    private fun stopService(){
        stopForeground(true)
        stopSelf()
    }
    private fun setup(){

        var startTime = System.currentTimeMillis()
        startTime += waitTime

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val popIntent = Intent(this,MainActivity::class.java)
        popIntent.putExtra(INTENT_COMMAND, INTENT_COMMAND_POPQUIZ)
        val pendingPop =
            PendingIntent.getService(this,0,popIntent,0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            startTime,
            waitTime,
            pendingPop
        )

    }

    private fun createRunningNotification(){

        //if were on a new build version, create notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            //create the channel
            val serviceChannel = NotificationChannel(
                POP_QUIZ_NOTIFICATION_CHANNEL,
                "Pop!Quiz Notification Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            //get a copy of the notification manager
            val notificationManager = getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            //add our channel
            notificationManager.createNotificationChannel(serviceChannel)
        }

        //Create the intent for stopping service
        val stopIntent =
            Intent(this, PopQuizService::class.java).apply {
                putExtra(INTENT_COMMAND, INTENT_COMMAND_STOP)
            }

        //create the flag for the pending intent based on version
        val flag =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_CANCEL_CURRENT
            else PendingIntent.FLAG_CANCEL_CURRENT

        //create the pending intent
        val exitPendingIntent =
            PendingIntent.getService(
                this,3,stopIntent,flag
            )

        //build the notification
        val notification = NotificationCompat.Builder(
            this, POP_QUIZ_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .addAction(0,"Stop",exitPendingIntent)
            .build()

        //Start the service with our notification
        startForeground(2,notification)

        setup()


    }

    private fun takeQuizNotification(){

        val notificationIntent =
            Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                0)

        val popNotification = NotificationCompat.Builder(
            this, POP_QUIZ_NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Pop! Quiz")
            .setContentText("It's time for a pop quiz!")
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(2,popNotification)
//        startForeground(1,popNotification)

    }

}