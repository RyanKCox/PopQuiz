package com.revature.popquiz.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.dataobjects.Quiz


import com.revature.popquiz.model.room.RoomDataManager

import com.revature.popquiz.viewmodel.CreateQuizVM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Quiz singleton for creating new and editing
 */
object QuizEditor {

    var focusQuiz = Quiz()
    //var mergeManger = Merge()


    //Repo for Room database
    private val quizRepository = RoomDataManager.quizRepository

    /**
     * Creates a new quiz for creation
     */
    fun createNewQuiz(){
        focusQuiz = Quiz()
    }

    fun setQuiz(quiz:Quiz){
        focusQuiz = quiz
    }

    /**
     * Save quiz to room database
     */
    suspend fun saveQuizToRoom() {

  RoomDataManager.quizRepository.insertQuiz(focusQuiz)

    }
}