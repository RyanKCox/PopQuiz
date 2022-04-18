package com.revature.popquiz.model.api.services.popquiz

import com.google.gson.annotations.SerializedName
import com.revature.popquiz.model.dataobjects.PopQuiz

/**
 * Response object for retrieving data from the server
 */
data class ResponsePopQuiz(

    @SerializedName("popquzzes")
    var results: ArrayList<PopQuiz>
)