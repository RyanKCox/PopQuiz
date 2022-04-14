package com.revature.popquiz.model.dataobjects

class Quiz {
    val questionList= mutableListOf<Question>()
    val resourceList= mutableListOf<QuizResource>()
    val tagList= mutableListOf<String>()
    var title=""
    var shortDescription=""
    var longDescription=""
    var score=0
    var progress:Float=0F
    var sampleQuestion="What is an example of a sample question?"

}