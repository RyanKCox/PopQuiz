package com.revature.popquiz.util

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(context: Context, userParameters: WorkerParameters, ): Worker(context, userParameters) {
    override fun doWork(): Result {
        return try {
            for (i in 0..10) {
                Log.i("NotificationWorker", "Counted $i")
            }
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}