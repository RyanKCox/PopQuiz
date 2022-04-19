package com.revature.popquiz.model.dataobjects

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "quiz")
data class Quiz(
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
)

