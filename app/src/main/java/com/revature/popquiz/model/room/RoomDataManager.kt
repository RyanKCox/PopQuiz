package com.revature.popquiz.model.room

import com.revature.popquiz.model.room.answerroom.AnswerRepository
import com.revature.popquiz.model.room.questionroom.QuestionRepository
import com.revature.popquiz.model.room.quizroom.QuizRepository

object RoomDataManager {

     lateinit var quizRepository: QuizRepository
     lateinit var questionRepository: QuestionRepository
     lateinit var answerRepository: AnswerRepository

}