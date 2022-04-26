package com.revature.popquiz.model.api.services.quiz

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revature.popquiz.model.api.services.QuizApiService
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import java.lang.Exception

class QuizByIDRepo(val quizService: QuizApiService)
{
    sealed class Result{
        object Loading:Result()
        data class Success(val quiz: LiveData<QuizAPIEntity>):Result()
        data class Failure(val throwable: Throwable):Result()
    }

    suspend fun fetchQuizResponse(id: Int): Result
    {

        return try {
            val result =
                quizService.getQuizById(id)
//                quizService.getQuizzes(RequestAllQuizzes()).quizList

//            val quizList:MutableList<Quiz> = mutableListOf()
            val quiz = QuizAPIEntity(
                title = "",
                shortDesc = "",
                longDesc = "",
                questionIDs = arrayListOf(),
                APIid = 0
            )

            quiz.title = result.quizDataList.quizDataTitle
            quiz.APIid = result.quizDataList.quizDataId
            result.quizDataList.quizPoolsList.forEach{
                quiz.shortDesc = it.quizPoolsDescription?: "null"
                quiz.longDesc = it.quizPoolsDescription?: "null"
                it.quizPoolQuestionsList.forEach {
                    quiz.questionIDs.add(it.quizPoolsQuestion.quizPoolQuestionResponseId)

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

            Log.d("Load Quiz", "Success " + result)
            Result.Success(MutableLiveData(quiz))

        } catch (e: Exception)
        {
            Log.d("Load Quizzes","Failed: ${e.message}")
            Result.Failure(e)
        }
    }
}