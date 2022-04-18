package com.revature.popquiz.model.api.services

import com.revature.popquiz.model.api.services.popquiz.RequestPopQuiz
import com.revature.popquiz.model.api.services.popquiz.ResponsePopQuiz
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Service interface containing all service calls for the PopQuiz Api
 */
interface QuizApiService {
    /**
     * Retrieves Pop Quiz from the server
     */
    @POST("popquiz")
    suspend fun getPopQuiz(@Body RequestPopQuiz: RequestPopQuiz): ResponsePopQuiz
}