package com.revature.popquiz.model.room.quizroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuizDao {
    @Query("SELECT * FROM quiz")
    fun fetchAllQuiz(): LiveData<List<QuizEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuiz(quiz: QuizEntity):Long

    @Query("DELETE FROM quiz WHERE id=:id")
    suspend fun deleteQuiz(id:Int)

    @Query("SELECT * FROM quiz WHERE title LIKE '%' || :search || '%' " +
            "OR shortDescription LIKE  '%' || :search || '%' " +
            "OR longDescription LIKE '%' || :search || '%' ")
    fun fetchWithSearch(search:String): LiveData<List<QuizEntity>>

}