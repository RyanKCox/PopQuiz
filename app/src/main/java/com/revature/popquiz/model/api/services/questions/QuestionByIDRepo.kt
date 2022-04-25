package com.revature.popquiz.model.api.services.questions

import androidx.lifecycle.LiveData
import com.revature.popquiz.model.api.services.QuizApiService
import com.revature.popquiz.model.api.services.quiz.QuizAPIEntity
import com.revature.popquiz.model.api.services.quiz.QuizByIDRepo
import com.revature.popquiz.model.dataobjects.Question

class QuestionByIDRepo(val quizService: QuizApiService)
{
    sealed class Result{
        object Loading:Result()
        data class Success(val quiz: LiveData<Question>):Result()
        data class Failure(val throwable: Throwable):Result()
    }

    suspend fun fetchQuestionById(id: Int): Result
    {
        return try
        {
            val result = quizService.getQuestionById(id)

            val question = Question()
            if (result.retrieveQuestionByIDList.questionType.questionTypeResponseType == )
        }
        catch (e: Exception)
        {
            Result.Failure(e)
        }
    }
}