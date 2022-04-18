package com.revature.popquiz.model.dataobjects

import javax.inject.Inject

class Quiz @Inject constructor()
{
    val questionList= mutableListOf<Question>()
    val resourceList= mutableListOf<String>()
    val tagList= mutableListOf<String>()
    var title=""
    var shortDescription=""
    var longDescription=""
    var score=0
    var progress:Float=0F
    var sampleQuestion="What is an example of a sample question?"

}