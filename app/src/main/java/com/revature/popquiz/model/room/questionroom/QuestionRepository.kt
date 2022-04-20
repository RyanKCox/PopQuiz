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
    suspend fun deleteQuiz(id:Int){
        questionDao.deleteQuestion(id)
    }
    suspend fun insertQuiz(question: QuestionEntity){
        questionDao.insertQuestion(question = question )
        Log.d("jcstn","Inside insert question :$question")
    }
    val fetchAllQuiz: LiveData<List<QuestionEntity>> = questionDao.fetchAllQuestion()
}