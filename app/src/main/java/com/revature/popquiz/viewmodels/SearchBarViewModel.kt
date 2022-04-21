package com.revature.popquiz.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revature.popquiz.model.api.RetrofitHelper
import com.revature.popquiz.model.api.services.quiz.AllQuizRepo
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.quizroom.QuizEntity


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SearchBarViewModel: ViewModel()
{
    private val quizService = RetrofitHelper.getAllQuizzesServices()
    private lateinit var quizRepo:AllQuizRepo

    var sSearchValue by mutableStateOf("")
    var quizList = mutableListOf<QuizEntity>()

    var sortedList:List<QuizEntity> by mutableStateOf(listOf())

    fun sortBySearch()
    {
        var tempList = mutableListOf<QuizEntity>()
        quizList.sortWith(compareBy{it.title})
        quizList.forEach()
        {
            if (
                it.title.contains(sSearchValue, ignoreCase = true) ||
                it.shortDescription.contains(sSearchValue, ignoreCase = true)
            )
            {
                tempList.add(it)
            }
        }
        sortedList = tempList.toList()

    }

    init
    {
        viewModelScope.launch(Dispatchers.IO)
        {
            loadQuizzes()
        }
    }

    private suspend fun loadQuizzes()
    {
        quizRepo = AllQuizRepo(quizService)

        when (val response = quizRepo.fetchQuizResponse()){

            is AllQuizRepo.Result.Success->
            {
                quizList = response.quizList.toMutableList()

                var tempList = mutableListOf<QuizEntity>()
                tempList.addAll(quizList)
                sortedList = tempList.toList()
            }
            is AllQuizRepo.Result.Failure->
            {
                Log.d("SearchVM", "Loading Failed")
            }
        }
    }
}