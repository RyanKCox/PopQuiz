package com.revature.popquiz.model.api.services.quiz

import com.google.gson.annotations.SerializedName
import com.revature.popquiz.model.dataobjects.Quiz

/**
 * Response object for retrieving data from the server
 */
data class ResponseAllQuizzes (

    @SerializedName("quizzes")
    var results:ArrayList<Quiz>

)