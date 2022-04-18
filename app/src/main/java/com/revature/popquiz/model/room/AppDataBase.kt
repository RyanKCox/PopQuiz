package com.revature.popquiz.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revature.popquiz.model.dataobjects.Quiz


@Database(entities = [Quiz::class],version=1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun quizDao():QuizDao
    companion object{
        @Volatile
        private var INSTANCE:AppDataBase?=null
        fun getDataBase(context: Context):AppDataBase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }
            synchronized(this)
            {
                var instance= Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java,"QUIZDATA").build()

                INSTANCE=instance
                return instance
            }
        }
    }

}