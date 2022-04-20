package com.revature.popquiz.model.room.questionroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.revature.popquiz.model.QuestionInterface

@Entity(tableName = "question")
data class QuestionEntity (
    @PrimaryKey(autoGenerate = true)
    var questionId:Int,
    @ColumnInfo(name = "quizId")
    var quizId:Int,
    @ColumnInfo(name = "nType")
    var nType:Int,
    @ColumnInfo(name="question")
    var question:String,
    )
