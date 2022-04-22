package com.revature.popquiz.model.dataobjects

import androidx.room.Entity
import com.revature.popquiz.model.QuestionInterface

data class Question(

    var nType:Int=0,
    var question:String="",
    var answers:MutableList<Answer> =mutableListOf() )
//{
//    companion object{
//
//        //List of question types
//        const val QUESTION_TYPE_TRUE_FALSE = 0
//        const val QUESTION_TYPE_SINGLE_ANSWER = 1
//        const val QUESTION_TYPE_MULTI_ANSWER = 2
//
//    }
//
//    /**
//     * Function to get all the correct answers of a question
//     */
//    fun getCorrectAnswers():List<Answer>{
//        val correct = arrayListOf<Answer>()
//        answers.forEach { answer ->
//            if (answer.bCorrect){
//                correct.add(answer)
//            }
//        }
//        return correct
//    }
//
//    fun getQuestionType():String{
//        var sType = ""
//        if (nType == 0){
//            sType = "True or False"
//        } else if (nType == 1){
//            sType = "Single Answer"
//        } else if (nType == 2) {
//            sType = "MultiAnswer"
//        }
//        return sType
//    }
//
//
//
//}