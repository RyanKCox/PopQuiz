package com.revature.popquiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.QuizEditor
import com.revature.popquiz.model.dataobjects.Quiz
import kotlinx.coroutines.launch

class CreateQuizVM : ViewModel() {

    //Fetch the quiz from our QuizEditor singleton
    var newQuiz = QuizEditor.focusQuiz

    /**
     * Save quiz through the editor
     */
    fun createNewQuiz(){
        QuizEditor.createNewQuiz()
        newQuiz = QuizEditor.focusQuiz
    }

    fun saveQuiz() {
        viewModelScope.launch { QuizEditor.saveQuizToRoom() }
    }
}