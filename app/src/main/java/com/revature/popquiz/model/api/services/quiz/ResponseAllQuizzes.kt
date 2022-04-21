package com.revature.popquiz.model.api.services.quiz

import com.google.gson.annotations.SerializedName
import com.revature.popquiz.model.dataobjects.Quiz

/**
 * Response object for retrieving data from the server
 */
data class ResponseAllQuizzes (

    @SerializedName("statusCode")
    var nStatusCode:Int,
    @SerializedName("description")
    var sResponseDesc:String,
    @SerializedName("data")
    var quizList:List<QuizResponse>


)
data class QuizResponse(

    @SerializedName("id")
    var nQuizID:Int,
    @SerializedName("title")
    var sTitle:String,
    @SerializedName("categoryName")
    var sCategoryName:String
)