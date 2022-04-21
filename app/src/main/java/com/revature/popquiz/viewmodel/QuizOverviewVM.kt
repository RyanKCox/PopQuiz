package com.revature.popquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.quizroom.QuizEntity

class QuizOverviewVM():ViewModel() {
    var quiz: Quiz?=null
}