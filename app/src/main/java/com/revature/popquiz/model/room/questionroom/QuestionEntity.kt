package com.revature.popquiz.model.room.questionroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.revature.popquiz.model.QuestionInterface

@Entity(tableName = "question")
data class QuestionEntity (
    @PrimaryKey(autoGenerate = true)
    var questionId:Int=0,
    @ColumnInfo(name = "quizId")
    var quizId:Int=0,
    @ColumnInfo(name = "nType")
    var nType:Int=0,
    @ColumnInfo(name="question")
    var question:String="",
    )
