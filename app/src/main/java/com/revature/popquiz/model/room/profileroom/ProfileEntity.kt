package com.revature.popquiz.model.room.profileroom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class ProfileEntity {
    @Entity(tableName = "profile")
    data class ProfileEntity (
        @PrimaryKey(autoGenerate = true)
        var userId:Int=0,
        @ColumnInfo(name = "quizId")
        var quizId:Int=0,
        @ColumnInfo(name = "BioID")
        var bioID:Int=0,
    )
}