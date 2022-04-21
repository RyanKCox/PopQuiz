package com.revature.popquiz.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.model.room.quizroom.QuizEntity
import com.revature.popquiz.viewmodels.QuizManager

class SavedQuizVM: ViewModel() {

//    private val quizService = RetrofitHelper.getAllQuizzesServices()
//    private lateinit var quizRepo: AllQuizRepo

    var sSearchValue by mutableStateOf("")
//    var quizList = QuizManager.usableQuizList

    var sortedList = RoomDataManager.quizRepository.fetchAllQuiz//MutableLiveData<List<QuizEntity>>(QuizManager.usableQuizList.value) //QuizManager.usableQuizList

    fun sortBySearch()
    {
        sortedList = if(sSearchValue != "") {
            RoomDataManager.quizRepository.fetchWithSearch(sSearchValue)
        } else{
            RoomDataManager.quizRepository.fetchAllQuiz
        }

//        var tempMasterList = mutableListOf<QuizEntity>()
//        var tempSortedList = mutableListOf<QuizEntity>()
//        tempMasterList.addAll(sortedList.value?: listOf())
//        tempMasterList.sortWith(compareBy{it.title})
//        tempMasterList.forEach()
//        {
//            if (
//                it.title.contains(sSearchValue, ignoreCase = true) ||
//                it.shortDescription.contains(sSearchValue, ignoreCase = true)
//            )
//            {
//                tempSortedList.add(it)
//            }
//        }
//        sortedList.

    }

//    init
//    {
//        viewModelScope.launch(Dispatchers.IO)
//        {
//            loadQuizzes()
//        }
//    }

//    private suspend fun loadQuizzes() {
//
////        quizRepo = AllQuizRepo(quizService)
////
////        when (val response = quizRepo.fetchQuizResponse()){
////
////            is AllQuizRepo.Result.Success->
////            {
////                quizList = response.quizList.toMutableList()
////
////                var tempList = mutableListOf<QuizEntity>()
////                tempList.addAll(quizList)
////                sortedList = tempList.toList()
////            }
////            is AllQuizRepo.Result.Failure->
////            {
////                Log.d("SearchVM", "Loading Failed")
////            }
////        }
//    }
}