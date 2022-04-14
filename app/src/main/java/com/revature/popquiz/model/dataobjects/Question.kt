package com.revature.popquiz.model.dataobjects

class Question(
    
    val nType:Int,
    var question:String,
    var answers:List<Answer> )
{
    companion object{

        //List of question types
        const val QUESTION_TYPE_TRUE_FALSE = 0
        const val QUESTION_TYPE_SINGLE_ANSWER = 1
        const val QUESTION_TYPE_MULTI_ANSWER = 2

    }

    /**
     * Function to get all the correct answers of a question
     */
    fun getCorrectAnswers():List<Answer>{
        val correct = arrayListOf<Answer>()
        answers.forEach { answer ->
            if (answer.bCorrect){
                correct.add(answer)
            }
        }
        return correct
    }



}