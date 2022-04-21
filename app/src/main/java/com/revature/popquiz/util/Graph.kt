package com.revature.popquiz.util

import android.content.Context
import androidx.room.RoomDatabase

object Graph {

    lateinit var database: RoomDatabase
    lateinit var appContext: Context

    val popQuizRepository by lazy {
//        PopQuizRepository(popQuizDao = database.popQuizDao())
    }

    fun provide(context: Context) {
        appContext = context
//        database = Room.databaseBuilder(context, RoomDatabase::class.java, "Data")
    }
}