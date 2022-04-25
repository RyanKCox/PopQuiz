package com.revature.popquiz.model

import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question

interface QuestionInterface {

    companion object{

        //List of question types
        const val QUESTION_TYPE_TRUE_FALSE = 0
        const val QUESTION_TYPE_SINGLE_ANSWER = 1
        const val QUESTION_TYPE_MULTI_ANSWER = 2
        const val QUESTION_STRING_TRUE_FALSE = "True or False"
        const val QUESTION_STRING_SINGLE_ANSWER = "Best Choice"
        const val QUESTION_STRING_MULTI_ANSWER = "Multiple Choice"


        fun getQuestionType(question:Question):String{
            var sType = ""
            when (question.nType) {
                QUESTION_TYPE_TRUE_FALSE -> {
                    sType = "True or False"
                }
                QUESTION_TYPE_SINGLE_ANSWER -> {
                    sType = "Single Answer"
                }
                QUESTION_TYPE_MULTI_ANSWER -> {
                    sType = "MultiAnswer"
                }
            }
            return sType
        }
    }
}