package com.revature.popquiz.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.viewmodels.QuizManager
import kotlinx.coroutines.launch

class SavedQuizVM: ViewModel() {

    //Search value to filter quiz list
    var sSearchValue by mutableStateOf("")

    //Deletion Boolean
    var bDelete = mutableStateOf(false)
    var nDeleteID:Int?=null

    //Initialize the quiz list from the room
    var sortedList = RoomDataManager.quizRepository.fetchAllQuiz

    //Sort list by search value
    fun sortBySearch()
    {
        sortedList = if(sSearchValue != "") {
            RoomDataManager.quizRepository.fetchWithSearch(sSearchValue)
        } else{
            RoomDataManager.quizRepository.fetchAllQuiz
        }
    }

    //Delete quiz from room database
    fun deleteQuiz(){
        viewModelScope.launch {
            if(nDeleteID != null){

                //Needs to include question and answers,
                //Change so they dont exist

                RoomDataManager.quizRepository.deleteQuiz(nDeleteID!!)
                nDeleteID = null
            }
        }
    }
}