package com.revature.popquiz.model.dataobjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "quiz")
data class Quiz @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo(name="questionList")
    val questionList: MutableList<Question> = mutableListOf<Question>(),
    @ColumnInfo(name="resourceList")
    val resourceList: MutableList<String> = mutableListOf<String>(),
    @ColumnInfo(name="tagList")
    val tagList: MutableList<String> = mutableListOf<String>(),
    @ColumnInfo(name = "title")
    var title: String ="",
    @ColumnInfo(name = "shortDescription")
    var shortDescription: String ="",
    @ColumnInfo(name = "longDescription")
    var longDescription: String ="",
    @ColumnInfo(name = "score")
    var score: Int =0,
    @ColumnInfo(name = "progress")
    var progress:Float=0F,
    @ColumnInfo(name = "sampleQuestion")
    var sampleQuestion: String ="What is an example of a sample question?"
)

