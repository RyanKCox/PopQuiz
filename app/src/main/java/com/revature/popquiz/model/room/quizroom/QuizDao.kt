package com.revature.popquiz.model.room.quizroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz")
    fun fetchAllQuiz(): List<QuizEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizEntity):Long
    @Query("DELETE FROM quiz WHERE id=:id")
    suspend fun deleteQuiz(id:Int)

}