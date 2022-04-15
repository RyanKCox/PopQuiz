package com.revature.popquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz

class EditQuizVM(var editQuiz: Quiz): ViewModel() {

    var editQuestionIndex: Int = 0

}
class EditQuizVMFactory(var editQuiz:Quiz) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Quiz::class.java).newInstance(editQuiz);
    }

}
