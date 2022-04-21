package com.revature.popquiz.view.screens.popquiz

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.PopQuizRepository
import com.revature.popquiz.model.dataobjects.PopQuizViewState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//foreground service

class PopQuizViewModel: ViewModel() {

    private val popQuizRepository = PopQuizRepository

    val _state = StateFlow<PopQuizViewState>
        get() = _state

    init {
        createNotificationChannel(context = )
        viewModelScope.launch {
            popQuizRepository.getTopic()
        }
    }
}
private fun createNotificationChannel(context: Context) {

    //Create the NotificationChannel
    //This is only available in SDK Version 26+
    /**
     * Higher notification importance: shows everywhere, makes noise and peeks. May use full screen intents.
     */

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = "NotificationChannelName"
        val descriptionText = "NotificationChannelDescriptionText"
        val importance = IMPORTANCE_HIGH
        val channel = NotificationChannel("CHANNEL_ID", notificationChannel, importance).apply {
            description = descriptionText
        }
        //Register the channel with the system
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}