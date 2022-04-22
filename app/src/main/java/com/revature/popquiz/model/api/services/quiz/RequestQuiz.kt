package com.revature.popquiz.model.api.services.quiz
import com.google.gson.annotations.SerializedName

data class ResponseRetrieveQuizByID(
    @SerializedName("statusCode")
    var quizStatusCode: Int,
    @SerializedName("description")
    var quizDescription: String,
    @SerializedName("data")
    var quizDataList: QuizDataResponse
)

data class QuizDataResponse(
    @SerializedName("id")
    var quizDataId: Int,
    @SerializedName("title")
    var quizDataTitle: String,
    @SerializedName("quizPools")
    var quizPoolsList:List<QuizPoolsResponse>,

)

data class QuizPoolsResponse(
    @SerializedName("id")
    var quizPoolsId: Int,
    @SerializedName("name")
    var quizPoolsName: String,
    @SerializedName("maxQstnToDisplay")
    var quizPoolsMaxQstns: Int,
    @SerializedName("displayOrder")
    var quizPoolsDisplayOrder: Int,
    @SerializedName("quizPoolQuestions")
    var quizPoolQuestionsList: List<QuizPoolQuestionsResponse>,
    @SerializedName("description")
    var quizPoolsDescription: String
)

data class QuizPoolQuestionsResponse(
    @SerializedName("id")
    var quizPoolsQuestionsId: Int,
    @SerializedName("question")
    var quizPoolsQuestionList: List<QuizPoolQuestionResponse>
)

data class QuizPoolQuestionResponse(
    @SerializedName("id")
    var quizPoolQuestionResponseId: Int
)
