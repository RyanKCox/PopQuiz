package com.revature.popquiz.model.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.revature.popquiz.model.dataobjects.QuizEntity


@Database(entities = [QuizEntity::class],version=1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun quizDao():QuizDao
    companion object{
        @Volatile
        private var INSTANCE:AppDataBase?=null
        fun getDataBase(context: Context):AppDataBase{
            var instance = INSTANCE
            if(instance!=null){
                return instance
            }

            synchronized(this)
            {
               val tempinstance= Room.databaseBuilder(context.applicationContext,
                    AppDataBase::class.java,"QUIZDATA").build()
                INSTANCE=tempinstance
                return tempinstance
            }
        }
    }

}