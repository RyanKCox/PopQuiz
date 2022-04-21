package com.revature.popquiz

import android.app.Application
import android.content.Context
import androidx.room.RoomDatabase
import dagger.hilt.android.HiltAndroidApp


//Applied context to the entire app, for use in the notification -Evan
@HiltAndroidApp
class PopQuizApp :Application() {

    lateinit var database: RoomDatabase
    lateinit var appContext: Context

    fun provide(context: Context) {
        appContext = context
//        database = Room.databaseBuilder(context, RoomDatabase::class.java, "Data")
    }
}