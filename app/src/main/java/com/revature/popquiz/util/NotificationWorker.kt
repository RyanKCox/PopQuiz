package com.revature.popquiz.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, userParameters: WorkerParameters, ): Worker(context, userParameters) {
    override fun doWork(): Result {
        TODO("Not yet implemented")
    }

}