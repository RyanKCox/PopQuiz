package com.revature.popquiz.model.api.services.quiz

import com.google.gson.annotations.SerializedName

/**
 * The request we send to the server
 */
data class RequestAllQuizzes(

    @SerializedName("size")
    val nSize:Int = 50,
    @SerializedName("page")
    val nPage:Int = 1,
    @SerializedName("sortOrder")
    val sSort:String = "asc",
    @SerializedName("orderBy")
    val sOrderBy:String = "title",
    @SerializedName("subscribedContent")
    val bSubscribed:Boolean = false,
    @SerializedName("publicContent")
    val bPublic:Boolean = false,
    @SerializedName("ownContent")
    val bOwn:Boolean = false,
    @SerializedName("showMine")
    val bShowMine:Boolean = false,
    @SerializedName("isOrdered")
    val bOrdered:Boolean = false,
//    @SerializedName("filteredContentTypes")
//    val filter = []
)