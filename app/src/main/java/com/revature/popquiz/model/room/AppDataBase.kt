package com.revature.popquiz.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.revature.popquiz.model.room.answerroom.AnswerDao
import com.revature.popquiz.model.room.questionroom.QuestionDao
import com.revature.popquiz.model.room.questionroom.QuestionEntity
import com.revature.popquiz.model.room.quizroom.QuizDao
import com.revature.popquiz.model.room.quizroom.QuizEntity


@Database(entities = [QuizEntity::class,QuestionEntity::class],version=1, exportSchema = false)
abstract class AppDataBase:RoomDatabase() {
    abstract fun quizDao(): QuizDao
    abstract fun questionDao(): QuestionDao
    abstract fun answerDao(): AnswerDao
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
                    AppDataBase::class.java,"QUIZDATA").build()
                INSTANCE =tempinstance
                return tempinstance
            }
        }
    }

}