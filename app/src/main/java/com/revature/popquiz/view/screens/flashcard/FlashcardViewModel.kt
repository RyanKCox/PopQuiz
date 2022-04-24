package com.revature.popquiz.view.screens.flashcard

import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz

/**
 * Store the quiz and whatever question we are on
 *
 */
class FlashcardViewModel: ViewModel() {

    var currentQuiz: Quiz? = null

    fun startFlashCards(quiz:Quiz){
        currentQuiz = quiz
    }

}