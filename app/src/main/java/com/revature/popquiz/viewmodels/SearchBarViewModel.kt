package com.revature.popquiz.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Quiz


class SearchBarViewModel: ViewModel()
{
    var sSearchValue by mutableStateOf("")
    var quizList = mutableListOf<Quiz>()
    var sortedList = mutableListOf<Quiz>()

    fun sortBySearch()
    {
        sortedList.clear()
        quizList.sortWith(compareBy{it.title})
        quizList.forEach()
        {
            if (
                it.title.contains(sSearchValue, ignoreCase = true) ||
                it.shortDescription.contains(sSearchValue, ignoreCase = true)
            )
            {
                sortedList.add(it)
            }
        }

    }

    init {
        loadQuizzes()
    }

    fun loadQuizzes()
    {
        quizList.add(
            Quiz(
                title = "Kotlin Fundamentals",
                shortDescription = "Goat",
                tagList = mutableListOf("Kotlin", "Beginners", "Kotlin for beginners")
            ),
        )
        quizList.add(
            Quiz(
                title = "Java Basics",
                shortDescription = "Java",
                tagList = mutableListOf("Java", "Easy", "OOP")
            ),
        )
        quizList.add(
            Quiz(
                title = "Kotlin",
                shortDescription = "Duck",
                tagList = mutableListOf("Kotlin", "Beginners", "Kotlin for beginners")
            ),
        )
        quizList.add(
            Quiz(
                title = "MVVM Kotlin",
                shortDescription = "Short quiz description",
                tagList = mutableListOf("Kotlin", "Beginners", "Kotlin for beginners")
            ),
        )
    }
}