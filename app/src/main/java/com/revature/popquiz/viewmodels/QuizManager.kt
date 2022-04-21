package com.revature.popquiz.viewmodels

import androidx.compose.runtime.mutableStateListOf
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.Merge
import com.revature.popquiz.model.room.RoomDataManager
import javax.inject.Singleton

@Singleton
object QuizManager
{

    var merge = Merge()
    var usableQuizList = mutableStateListOf<Quiz>()

    fun loadQuizzes()
    {
        usableQuizList.clear()
        var allQuizzes = RoomDataManager.quizRepository.fetchAllQuiz
        allQuizzes.forEach()
        {
            usableQuizList.add(merge.createQuizFromEntity(it))
        }
    }
}