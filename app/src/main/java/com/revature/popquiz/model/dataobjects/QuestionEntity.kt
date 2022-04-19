package com.revature.popquiz.model.dataobjects

import androidx.room.Entity
import com.revature.popquiz.model.QuestionInterface

@Entity
data class QuestionEntity (
    var nType:Int,
    var question:String,
    var answers:List<Answer> )
