package com.revature.popquiz.model.api.services.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.revature.popquiz.model.QuestionInterface
import com.revature.popquiz.model.api.services.QuizApiService
import com.revature.popquiz.model.api.services.quiz.QuizAPIEntity
import com.revature.popquiz.model.api.services.quiz.QuizByIDRepo
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.view.screens.createquiz.questionAnswers

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
            if (
                result.retrieveQuestionByIDList.questionType.questionTypeResponseType == QuestionInterface.QUESTION_STRING_TRUE_FALSE ||
                result.retrieveQuestionByIDList.questionType.questionTypeResponseType == QuestionInterface.QUESTION_STRING_MULTI_ANSWER ||
                result.retrieveQuestionByIDList.questionType.questionTypeResponseType == QuestionInterface.QUESTION_STRING_SINGLE_ANSWER
            )
            {
                when (result.retrieveQuestionByIDList.questionType.questionTypeResponseType)
                {
                    QuestionInterface.QUESTION_STRING_TRUE_FALSE -> question.nType = QuestionInterface.QUESTION_TYPE_TRUE_FALSE
                    QuestionInterface.QUESTION_STRING_SINGLE_ANSWER -> question.nType = QuestionInterface.QUESTION_TYPE_SINGLE_ANSWER
                    QuestionInterface.QUESTION_STRING_MULTI_ANSWER -> question.nType = QuestionInterface.QUESTION_TYPE_MULTI_ANSWER
                }
                question.question = result.retrieveQuestionByIDList.questionTitle
                result.retrieveQuestionByIDList.questionAnswers.forEach()
                {
                    val answer = Answer()
                    answer.sAnswer = it.questionAnswer
                    answer.bCorrect = it.questionAnswerCorrect
                    question.answers.add(answer)
                }

                Result.Success(MutableLiveData(question))
            }
            else
            {
                Result.Failure(Exception("Question type not supported."))
            }
        }
        catch (e: Exception)
        {
            Result.Failure(e)
        }
    }
}