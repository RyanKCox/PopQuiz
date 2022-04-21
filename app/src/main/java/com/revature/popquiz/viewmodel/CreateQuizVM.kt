package com.revature.popquiz.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.QuizEditor
import com.revature.popquiz.model.dataobjects.Quiz

class CreateQuizVM : ViewModel() {

    //Fetch the quiz from our QuizEditor singleton
    var newQuiz = QuizEditor.focusQuiz

    /**
     * Save quiz through the editor
     */
    fun saveQuiz() {
        QuizEditor.saveQuizToRoom()
    }
}