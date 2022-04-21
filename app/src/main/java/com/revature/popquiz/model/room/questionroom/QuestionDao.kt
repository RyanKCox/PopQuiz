package com.revature.popquiz.model.room.questionroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface QuestionDao {
    @Query("SELECT * FROM question")
    fun fetchAllQuestion(): LiveData<List<QuestionEntity>>

    @Query("SELECT * FROM question WHERE quizId=:id")
    fun fetchQuestionWithQuizId(id:Int): LiveData<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: QuestionEntity):Long

    @Query("DELETE FROM question WHERE questionId=:id")
    suspend fun deleteQuestion(id:Int)

}