package com.revature.popquiz.model.room.profileroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.revature.popquiz.view.screens.question.RunningQuiz


    @Entity(tableName = "profile")
    data class ProfileEntity (
        @PrimaryKey(autoGenerate = false)
        var email:String="",

        var bio:String="",

        var name:String="",

        var popQuizIntervals:Int=1,

        var pastQuizzes:MutableList<String> = mutableListOf()


    )
