package com.revature.popquiz

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

const val POP_QUIZ_NOTIFICATION_CHANNEL = "Pop!Quiz"
const val INTENT_COMMAND = "Command"
const val INTENT_COMMAND_REPLY = "Reply"

class PopQuizService: Service() {
    override fun onBind(p0: Intent?): IBinder? =null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val command = intent?.getStringExtra(INTENT_COMMAND)

        if(command =="Stop"){
            stopService()
            return START_NOT_STICKY
        } else if (command == INTENT_COMMAND_REPLY){
            Toast.makeText(this,"CLicked Reply",Toast.LENGTH_LONG).show()
        }

        showNotification()


        return START_NOT_STICKY
        //return super.onStartCommand(intent, flags, startId)
    }
    private fun stopService(){
        stopForeground(true)
        stopSelf()
    }

    private fun showNotification(){

    }

}