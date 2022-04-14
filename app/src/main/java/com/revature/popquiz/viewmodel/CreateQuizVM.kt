package com.revature.popquiz.viewmodel

import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Quiz

class CreateQuizVM: ViewModel() {

    //New quiz
    val newQuiz:Quiz = Quiz()
}