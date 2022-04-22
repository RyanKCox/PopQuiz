package com.revature.popquiz.model.room.quizroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.AppDataBase

class QuizRepository(application: Application) {
    private var quizDao: QuizDao

    init {
        var dataBase= AppDataBase.getDataBase(application)
        quizDao=dataBase.quizDao()
    }
    suspend fun deleteQuiz(id:Int){
        quizDao.deleteQuiz(id)
    }
    suspend fun insertQuiz(quiz: Quiz):Long{
       return quizDao.insertQuiz(quiz)
        Log.d("jcstn","Inside insert quiz :$quiz")
    }
    val fetchAllQuiz: LiveData<List<Quiz>> = quizDao.fetchAllQuiz()
    fun fetchWithSearch(search:String):LiveData<List<Quiz>>{
        return quizDao.fetchWithSearch(search)
    }
}