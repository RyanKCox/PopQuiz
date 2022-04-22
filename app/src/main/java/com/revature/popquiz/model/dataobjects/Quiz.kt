package com.revature.popquiz.model.dataobjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "quiz")
data class Quiz(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    val questionList: MutableList<Question> = mutableListOf<Question>(),

    val resourceList: MutableList<String> = mutableListOf<String>(),

    val tagList: MutableList<String> = mutableListOf<String>(),

    var title: String ="",

    var shortDescription: String ="",

    var longDescription: String ="",
)

