package com.revature.popquiz.model.api.services.flashcard

import com.google.gson.annotations.SerializedName
import com.revature.popquiz.model.dataobjects.Quiz

/**
 * Response object for retrieving data from the server
 */
data class ResponseAllQuizzes (

    @SerializedName("flashcards")
    var results:ArrayList<Flashcard>

)