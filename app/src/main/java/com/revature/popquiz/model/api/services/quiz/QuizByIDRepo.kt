package com.revature.popquiz.model.api.services.quiz

import android.util.Log
import com.revature.popquiz.model.api.services.QuizApiService
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import java.lang.Exception

class QuizByIDRepo(val quizService: QuizApiService)
{
    sealed class Result{
        object Loading:Result()
        data class Success(val quiz:Quiz):Result()
        data class Failure(val throwable: Throwable):Result()
    }

    suspend fun fetchQuizResponse(id: Int): AllQuizRepo.Result
    {

        return try {
            val result =
                quizService.getQuizById(id)
//                quizService.getQuizzes(RequestAllQuizzes()).quizList

//            val quizList:MutableList<Quiz> = mutableListOf()
            val quiz = Quiz()
            quiz.title = result.quizDataList.quizDataTitle
            result.quizDataList.quizPoolsList.forEach{
                quiz.shortDescription = it.quizPoolsDescription
                quiz.longDescription = it.quizPoolsDescription
                it.quizPoolQuestionsList.forEach {
                    it.quizPoolsQuestionList.forEach {
                        it.quizPoolQuestionResponseId
                        var question = Question()
                    }
                }
        }

//            result.forEach {
//                quizList.add(
//                    Quiz(
//                        id = it.nQuizID,
//                        title = it.sTitle,
//                        shortDescription = it.sCategoryName,
//                        longDescription = it.sCategoryName
//                    )
//                )
//            }

            Log.d("Load Quiz", "Success " + resultList.size)
            AllQuizRepo.Result.Success(quizList)

        } catch (e: Exception)
        {
            Log.d("Load Quizzes","Failed: ${e.message}")
            AllQuizRepo.Result.Failure(e)
        }
    }
}