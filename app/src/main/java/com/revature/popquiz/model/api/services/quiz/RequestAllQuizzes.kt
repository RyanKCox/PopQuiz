package com.revature.popquiz.model.api.services.quiz

import com.google.gson.annotations.SerializedName

/**
 * The request we send to the server
 */
data class RequestAllQuizzes(

    @SerializedName("sQuizTable")
    val sToyTable:String,
    @SerializedName("sScope")
    val sScope:String
)