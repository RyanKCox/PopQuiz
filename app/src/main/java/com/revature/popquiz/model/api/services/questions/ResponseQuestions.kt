package com.revature.popquiz.model.api.services.questions

import com.google.gson.annotations.SerializedName

data class ResponseRetrieveQuestionByID(

    @SerializedName("statusCode")
    var qIdStatusCode: Int,
    @SerializedName("description")
    var qIdDescription: String,
    @SerializedName("data")
    var retrieveQuestionByIDList: List<QuestionsResponse>
)

data class QuestionsResponse(

    @SerializedName("id")
    var questionId: Int,
    @SerializedName("title")
    var questionTitle: String,
    @SerializedName("levelId")
    var questionLevelId: Int,
    @SerializedName("qsnAnswers")
    var questionAnswers: List<QuestionAnswersResponse>,
    @SerializedName("qsnType")
    var questionType: List<QuestionTypeResponse>
)

data class QuestionAnswersResponse(
    @SerializedName("id")
    var questionAnswerId: Int,
    @SerializedName("answer")
    var questionAnswer: String,
    @SerializedName("order")
    var questionAnswerOrder: Int,
    @SerializedName("correct")
    var questionAnswerCorrect: Boolean
)

data class QuestionTypeResponse(
    @SerializedName("id")
    var questionTypeResponseId: Int
)
