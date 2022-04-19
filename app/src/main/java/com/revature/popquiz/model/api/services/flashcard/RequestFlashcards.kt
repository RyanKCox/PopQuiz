package com.revature.popquiz.model.api.services.flashcard

import com.google.gson.annotations.SerializedName

/**
 * The request we send to the server
 */
data class RequestFlashcards(

    @SerializedName("sFlashcards")
    val sToyTable: String,
    @SerializedName("sScope")
    val sScope: String
)