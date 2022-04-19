package com.revature.popquiz.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.domain.models.Quiz

class SearchBarViewModel: ViewModel()
{
    var sSearchValue by mutableStateOf("")
    var quizList by mutableListOf<Quiz>()

    fun sortBySearch()
    {

    }

    fun loadQuizzes()
    {

    }
}