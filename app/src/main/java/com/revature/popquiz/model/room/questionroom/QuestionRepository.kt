package com.revature.popquiz.model.room.questionroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.revature.popquiz.model.room.AppDataBase


class QuestionRepository(application: Application) {
    private var questionDao: QuestionDao

    init {
        var qdataBase= AppDataBase.getDataBase(application)
        questionDao=qdataBase.questionDao()
    }
    suspend fun deleteQuestion(id:Int){
        questionDao.deleteQuestion(id)
    }
    suspend fun insertQuestion(question: QuestionEntity):Long{
        return questionDao.insertQuestion(question = question )
        Log.d("jcstn","Inside insert question :$question")
    }
    val fetchAllQuestion: LiveData<List<QuestionEntity>> = questionDao.fetchAllQuestion()
    suspend fun fetchQuestionWithQuizId(id:Int):LiveData<List<QuestionEntity>> = questionDao.fetchQuestionWithQuizId(id)
}