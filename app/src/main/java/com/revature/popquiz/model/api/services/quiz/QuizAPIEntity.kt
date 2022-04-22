package com.revature.popquiz.model.api.services.quiz

data class QuizAPIEntity (

    var title:String,
    var shortDesc:String,
    var longDesc:String,
    var questionIDs:ArrayList<Int>
        )