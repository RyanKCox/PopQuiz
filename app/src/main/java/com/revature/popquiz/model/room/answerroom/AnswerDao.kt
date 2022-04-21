package com.revature.popquiz.model.room.answerroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface AnswerDao {
    @Query("SELECT * FROM answer")
    fun fetchAllAnswer(): LiveData<List<AnswerEntity>>

    @Query("SELECT * FROM answer WHERE questionId=:id")
    fun fetchAnswerWithQuestionId(id:Int): LiveData<List<AnswerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnswer(answer: AnswerEntity)

    @Query("DELETE FROM answer WHERE answerId=:id")
    suspend fun deleteAnswer(id:Int)

}