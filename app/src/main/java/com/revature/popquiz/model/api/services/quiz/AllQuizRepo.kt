package com.revature.popquiz.model.api.services.quiz

import android.util.Log
import com.revature.popquiz.model.api.services.QuizApiService
import com.revature.popquiz.model.dataobjects.QuizEntity
import java.lang.Exception

class AllQuizRepo(val quizService:QuizApiService) {

    sealed class Result{
        object Loading:Result()
        data class Success(val quizList:List<QuizEntity>):Result()
        data class Failure(val throwable: Throwable):Result()
    }

    suspend fun fetchQuizResponse():Result{

        return try {
            val resultList =
                quizService.getQuizzes(RequestAllQuizzes()).quizList

            val quizList:MutableList<QuizEntity> = mutableListOf()
            resultList.forEach {
                quizList.add(
                    QuizEntity(
                        id = it.nQuizID,
                        title = it.sTitle,
                        shortDescription = it.sCategoryName,
                        longDescription = it.sCategoryName
                    )
                )
            }

            Log.d("Load Quizzes", "Success " + resultList.size)
            Result.Success(quizList)

        } catch (e:Exception){
            Log.d("Load Quizzes","Failed: ${e.message}")
            Result.Failure(e)

        }
    }
}