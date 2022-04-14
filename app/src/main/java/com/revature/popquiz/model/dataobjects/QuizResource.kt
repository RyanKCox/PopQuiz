package com.revature.popquiz.model.dataobjects

class QuizResource(var sLink:String) {

    fun getResourceShortName():String{
        return sLink.substringAfterLast('/')
    }
}