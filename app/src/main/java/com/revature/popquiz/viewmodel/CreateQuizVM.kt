package com.revature.popquiz.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateQuizVM @Inject constructor(val newQuiz: Quiz): ViewModel(), LifecycleObserver {

    //New quiz

}