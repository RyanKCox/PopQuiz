package com.revature.popquiz.model.api.services

import com.revature.popquiz.model.dataobjects.PopQuiz
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Service interface containing all service calls for the PopQuiz Api
 */
interface PopQuizApiService {

    /**
     * Retrieves Pop Quiz from the server
     */
    @POST("popquiz")
    suspend fun getPopQuiz(@Body RequestPopQuiz: RequestPopQuiz): PopQuiz
}