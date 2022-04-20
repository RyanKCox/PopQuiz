package com.revature.popquiz.model.room.answerroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.revature.popquiz.model.room.AppDataBase
import com.revature.popquiz.model.room.questionroom.QuestionDao
import com.revature.popquiz.model.room.questionroom.QuestionEntity

class AnswerRepository(application: Application) {
    private var answerDao: AnswerDao

    init {
        var adataBase= AppDataBase.getDataBase(application)
        answerDao=adataBase.answerDao()
    }
    suspend fun deleteAnswer(id:Int){
        answerDao.deleteAnswer(id)

    }
    suspend fun insertAnswer(answer: AnswerEntity){
        answerDao.insertAnswer(answer = AnswerEntity() )
        Log.d("jcstn","Inside insert answer :$answer")
    }
    val fetchAllAnswer: LiveData<List<AnswerEntity>> = answerDao.fetchAllAnswer()
}