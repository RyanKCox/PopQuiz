package com.revature.popquiz.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.QuizEditor
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.RoomDataManager
import kotlinx.coroutines.launch

class EditQuizVM(): ViewModel() {

    var editQuestionIndex: Int = 0
    var nDeleteQuestionIndex:Int? = null
    var editQuiz = QuizEditor.focusQuiz

    fun updateQuizToRoom(){
        viewModelScope.launch {

            RoomDataManager.quizRepository.insertQuiz(editQuiz)
        }
    }

}
//class EditQuizVMFactory(var editQuiz:Quiz) : ViewModelProvider.Factory {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return modelClass.getConstructor(Quiz::class.java).newInstance(editQuiz);
//    }
//
//}
