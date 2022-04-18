package com.revature.popquiz.model.room

import android.app.Application
import androidx.lifecycle.LiveData
import com.revature.popquiz.model.dataobjects.Quiz

class QuizRepository(application: Application) {
    private lateinit var quizDao: QuizDao

    init {
        var dataBase=AppDataBase.getDataBase(application)
        quizDao=dataBase.quizDao()
    }
    suspend fun deleteQuiz(id:Int){
        quizDao.deleteQuiz(id)
    }
    suspend fun insertQuiz(quiz: Quiz){
        quizDao.insertQuiz(quiz)
    }
    val fetchAllQuiz:LiveData<List<Quiz>> = quizDao.fetchAllQuiz()
}