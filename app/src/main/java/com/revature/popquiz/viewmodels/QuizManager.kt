package com.revature.popquiz.viewmodels

import androidx.compose.runtime.livedata.observeAsState
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.Merge
import com.revature.popquiz.model.room.RoomDataManager
import javax.inject.Singleton

@Singleton
object QuizManager
{

    var merge = Merge()
    var usableQuizList = mutableListOf<Quiz>()

    fun loadQuizzes()
    {
        var allQuizzes = RoomDataManager.quizRepository.fetchAllQuiz
        allQuizzes.forEach()
        {
            usableQuizList.add(merge.createQuizFromEntity(it))
        }
    }
}