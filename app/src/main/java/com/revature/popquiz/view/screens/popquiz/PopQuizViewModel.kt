package com.revature.popquiz.view.screens.popquiz

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.content.Context
import android.os.Build
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.*
import com.revature.popquiz.util.Graph
import com.revature.popquiz.model.PopQuizRepository
import com.revature.popquiz.model.dataobjects.PopQuiz
import com.revature.popquiz.util.NotificationWorker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

//foreground service

class PopQuizViewModel: ViewModel() {

    private val popQuizRepository = PopQuizRepository
    private val _state = MutableStateFlow(PopQuiz)

    val state: StateFlow<PopQuiz.Companion>
        get() = _state

    init {
        createNotificationChannel(context = Graph.appContext)
        viewModelScope.launch {
            popQuizRepository.getTopic()
        }
    }
}

//With this method you can set the constraints required for the notification to show
private fun setOneTimeNotification() {
    val workManager = WorkManager.getInstance(Graph.appContext)
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
    val notificationWorker = OneTimeWorkRequestBuilder<NotificationWorker>()
        .setInitialDelay(10, TimeUnit.SECONDS)
        .setConstraints(constraints)
        .build()

    //add the notification to be "worked on" in the background
    workManager.enqueue(notificationWorker)

    //monitor the state of the worker
    workManager.getWorkInfoByIdLiveData(notificationWorker.id)
        .observeForever { workInfo ->
            if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                createSuccessNotification()
            } else {
                createErrorNotification()
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