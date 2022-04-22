package com.revature.popquiz.model.dataobjects


data class PopQuiz (
    val nType: Int,
    val question: Question,
    val answer: List<Answer> = emptyList()
) {
    companion object {

    }

//class PopQuiz {
//    val question = Question()
//    var title = ""
//    var answer = Answer()

}