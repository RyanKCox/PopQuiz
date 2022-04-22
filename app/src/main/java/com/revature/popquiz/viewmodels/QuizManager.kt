package com.revature.popquiz.viewmodels

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.RoomDataManager
import javax.inject.Singleton

@Singleton
object QuizManager
{
//    var merge = Merge()
    var usableQuizList = RoomDataManager.quizRepository.fetchAllQuiz//mutableStateListOf<Quiz>()
    fun loadQuizzes()
    {
        usableQuizList = RoomDataManager.quizRepository.fetchAllQuiz
//        usableQuizList.clear()
//        var allQuizzes = RoomDataManager.quizRepository.fetchAllQuiz
//        allQuizzes.forEach()
//        {
//            usableQuizList.add(merge.createQuizFromEntity(it))
//        }
    }
}