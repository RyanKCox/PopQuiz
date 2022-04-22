package com.revature.popquiz.model.api.services

import com.revature.popquiz.model.api.services.popquiz.RequestPopQuiz
import com.revature.popquiz.model.api.services.popquiz.ResponsePopQuiz
import com.revature.popquiz.model.api.services.questions.ResponseRetrieveQuestionByID
import com.revature.popquiz.model.api.services.quiz.RequestAllQuizzes
import com.revature.popquiz.model.api.services.quiz.ResponseAllQuizzes
import com.revature.popquiz.model.api.services.quiz.ResponseRetrieveQuizByID
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Service interface containing all service calls for the PopQuiz Api
 */
interface QuizApiService {

    /**
     * Retrieves all quizzes from the server
     */
    @POST("/quiz/secure/quizzes")
    suspend fun getQuizzes(@Body RequestAllQuizzes: RequestAllQuizzes): ResponseAllQuizzes

    //Retrieves Pop Quiz from the server
    @POST("popquiz")
    suspend fun getPopQuiz(@Body RequestPopQuiz: RequestPopQuiz): ResponsePopQuiz

    // Retrieves quiz by ID
    @GET("/quiz/secure/1")
    suspend fun getQuizById(): ResponseRetrieveQuizByID

    // Retrieve quiz question by ID
    @GET("/quiz/secure/questions/1")
    suspend fun getQuestionById() : ResponseRetrieveQuestionByID
}