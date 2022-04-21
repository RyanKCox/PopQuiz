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

data class ResponseRetrieveQuestionByID(

    @SerializedName("qIdStatusCode")
    var qIdStatusCode: Int,
    @SerializedName("qIdDescription")
    var qIdDescription: String,
    @SerializedName("qIdData")
    var retrieveQuestionByIDList: List<QuestionsResponse>
)

data class QuestionsResponse(

    @SerializedName("questionId")
    var questionId: Int,
    @SerializedName("questionTitle")
    var questionTitle: String,
    @SerializedName("questionLevelId")
    var questionLevelId: Int,
    var questionAnswers: List<QuestionAnswersResponse>
)

data class QuestionAnswersResponse(
    @SerializedName("questionAnswerId")
    var questionAnswerId: Int,
    @SerializedName("questionAnswer")
    var questionAnswer: String,
    @SerializedName("questionAnswerOrder")
    var questionAnswerOrder: Int,
    @SerializedName("questionAnswerCorrect")
    var questionAnswerCorrect: Boolean
)