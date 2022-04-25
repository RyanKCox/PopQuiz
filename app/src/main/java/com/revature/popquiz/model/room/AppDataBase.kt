package com.revature.popquiz.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.profileroom.ProfileDao
import com.revature.popquiz.model.room.profileroom.ProfileEntity
import com.revature.popquiz.model.room.quizroom.QuizDao



@Database(entities = [Quiz::class,ProfileEntity::class],version=6, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase:RoomDatabase() {
    abstract fun quizDao(): QuizDao
    abstract fun profileDao():ProfileDao

    companion object{
        @Volatile
        private var INSTANCE: AppDataBase?=null
        fun getDataBase(context: Context): AppDataBase {
            var instance = INSTANCE
            if(instance!=null){
                return instance
            }

            synchronized(this)
            {
               val tempinstance= Room.databaseBuilder(context.applicationContext,
                    AppDataBase::class.java,"QUIZDATA1").fallbackToDestructiveMigration().build()
                INSTANCE =tempinstance
                return tempinstance
            }
        }
    }

}