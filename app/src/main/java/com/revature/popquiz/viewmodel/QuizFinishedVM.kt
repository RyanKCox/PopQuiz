package com.revature.popquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.revature.popquiz.view.screens.question.RunningQuiz

class QuizFinishedVM:ViewModel() {

    var quiz = RunningQuiz()

    fun getResultText():String{

        return when {
            quiz.finalScore == 100.0f -> {
                "Perfect!"
            }
            quiz.finalScore!! >= 90.0f -> {
                "Wonderful!"
            }
            quiz.finalScore!! >= 80.0f -> {
                "Good Job!"
            }
            else -> {
                "Need to Review"
            }
        }
    }
}