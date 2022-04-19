package com.revature.popquiz.model.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.dataobjects.QuizEntity

class QuizRepository(application: Application) {
    private var quizDao: QuizDao

    init {
        var dataBase=AppDataBase.getDataBase(application)
        quizDao=dataBase.quizDao()
    }
    suspend fun deleteQuiz(id:Int){
        quizDao.deleteQuiz(id)
    }
    suspend fun insertQuiz(quiz: QuizEntity){
        quizDao.insertQuiz(quiz)
        Log.d("jcstn","Inside insert quiz :$quiz")
    }
    val fetchAllQuiz:LiveData<List<QuizEntity>> = quizDao.fetchAllQuiz()
}