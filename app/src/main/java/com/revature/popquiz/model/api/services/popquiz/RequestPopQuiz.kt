package com.revature.popquiz.model.api.services.popquiz

import com.google.gson.annotations.SerializedName

/**
 * The request to send to the server
 */
data class RequestPopQuiz(

    @SerializedName("sPopQuiz")
    val sPopQuiz: String,
    @SerializedName("sScope")
    val sScope: String
)