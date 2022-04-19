package com.revature.popquiz.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.dataobjects.QuizEntity
import com.revature.popquiz.model.room.RoomDataManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class CreateQuizVM : ViewModel() {
    val newQuiz: Quiz=Quiz()
    val quizRepository = RoomDataManager.quizRepository

    fun saveQuiz(quiz:Quiz) {
        val quizEntity = QuizEntity(title = quiz.title, shortDescription = quiz.shortDescription)
        viewModelScope.launch {
            quizRepository.insertQuiz(quiz = quizEntity)
            Log.d("jcstn","QuizEntity: $quizEntity /n , quiz: $quiz")
        }
    }
    //New quiz

}