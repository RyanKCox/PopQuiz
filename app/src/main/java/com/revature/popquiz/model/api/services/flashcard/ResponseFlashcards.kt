package com.revature.popquiz.model.api.services.flashcard

import com.google.gson.annotations.SerializedName
import com.revature.popquiz.model.dataobjects.Flashcard

/**
 * Response object for retrieving data from the server
 */
data class ResponseFlashcards (

    @SerializedName("flashcards")
    var results:ArrayList<Flashcard>

)