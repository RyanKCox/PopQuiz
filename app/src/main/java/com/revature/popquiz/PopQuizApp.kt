package com.revature.popquiz

import android.app.Application
import com.revature.popquiz.util.Graph
import dagger.hilt.android.HiltAndroidApp


//Applied context to the entire app, for use in the notification -Evan
@HiltAndroidApp
class PopQuizApp :Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}