package com.revature.popquiz.model.room.answerroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "answer")
data class AnswerEntity (
    @PrimaryKey(autoGenerate = true)
    var answerId:Int=0,
    @ColumnInfo(name = "questionId")
    var questionId:Int=0,
    @ColumnInfo(name = "sAnswer")
    var sAnswer:String="",
    @ColumnInfo(name = "bCorrect")
    var bCorrect:Boolean=false
)