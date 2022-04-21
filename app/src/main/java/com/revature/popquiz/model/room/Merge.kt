package com.revature.popquiz.model.room

import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.answerroom.AnswerEntity
import com.revature.popquiz.model.room.questionroom.QuestionEntity
import com.revature.popquiz.model.room.quizroom.QuizEntity
import com.revature.popquiz.view.screens.quizTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Merge
{
    val scope=CoroutineScope(Dispatchers.IO)

    fun createQuizFromEntity(quizEntity: QuizEntity):Quiz
    {
        var quizToReturn:Quiz=Quiz()
        scope.launch {
            val quiz = Quiz(
                title = quizEntity.title,
                shortDescription = quizEntity.shortDescription,
                longDescription = quizEntity.longDescription
            )
            val questions =
                RoomDataManager.questionRepository.fetchQuestionWithQuizId(id = quizEntity.id)
            questions.value?.forEach {qe->
                val answersE =RoomDataManager.answerRepository.fetchAnswerWithQuestionId(qe.questionId)
                val answers:MutableList<Answer> = mutableListOf()
                answersE.value?.forEach { ae->
                    answers.add(Answer(sAnswer = ae.sAnswer, bCorrect = ae.bCorrect))
                }
                quiz.questionList.add(Question(nType = qe.nType, question = qe.question, answers = answers ))

            }
            //convert tags
            stringToList(quizEntity.tags).forEach {
                quiz.tagList.add(it)
            }
            //convert resources
            stringToList(quizEntity.resources).forEach {
                quiz.resourceList.add(it)
            }
            quizToReturn=quiz

        }
        return quizToReturn
    }

    fun saveQuizToRoom(quiz: Quiz)
    {

        val quizEntity=QuizEntity(
            title=quiz.title,
            shortDescription =quiz.shortDescription,
            longDescription = quiz.longDescription,
            tags = listToString(quiz.tagList),
            resources = listToString(quiz.resourceList)
        )
        scope.launch {
            val quizId=RoomDataManager.quizRepository.insertQuiz(quizEntity).toInt()
            quiz.questionList.forEach { question->
                val questionEntity =QuestionEntity(
                    quizId = quizId,
                    nType = question.nType,
                    question = question.question
                )
                val questionId=RoomDataManager.questionRepository.insertQuestion(questionEntity).toInt()
                question.answers.forEach { answer->
                    val answerEntity = AnswerEntity(
                        questionId = questionId,
                        sAnswer = answer.sAnswer,
                        bCorrect = answer.bCorrect
                    )
                    RoomDataManager.answerRepository.insertAnswer(answerEntity)

                }
            }
        }

    }


    fun stringToList(tag:String):List<String>
    {
        return tag.split("\n")

    }
    fun listToString(list: List<String>): String {
        val separator = "\n"
        return list.joinToString(separator = separator)

    }

}





//quiz.questionList.add(Question(nType = it.nType, question = it.question, answers = ))






//fun addAnswers(questions: MutableList<Question>):MutableList<AnswerEntity>
//{
//    var answers:MutableList<AnswerEntity> = mutableListOf()
//    questions.forEach {question->
//        question.answers.forEach {answer->
//            answers.add(AnswerEntity(sAnswer = answer.sAnswer))
//        }
//    }
//
//}