package com.revature.popquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.Merge
import com.revature.popquiz.model.room.quizroom.QuizEntity

class QuizOverviewVM():ViewModel() {
    //var quiz: QuizEntity?=null
    var quiz:Quiz?=null

    fun setQuiz(quizEntity: QuizEntity){
        quiz = Merge.createQuizFromEntity(quizEntity)
    }
}