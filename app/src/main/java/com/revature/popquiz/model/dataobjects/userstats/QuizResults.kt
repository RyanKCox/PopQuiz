package com.revature.popquiz.model.dataobjects.userstats

data class QuizResults(

    var nQuizId:Int,
    var fQuizProgress:Float,
    var arQuizScores:ArrayList<Float>
)
