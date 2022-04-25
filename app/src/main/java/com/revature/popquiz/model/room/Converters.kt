package com.revature.popquiz.model.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.view.screens.question.RunningQuiz
import java.util.*

class Converters {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<Question?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val type = object : TypeToken<List<Question>>() {}.type
        return gson.fromJson(data, type)
    }
    @TypeConverter
    fun stringToSomeRunningQuizzes(data: String?): List<RunningQuiz?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val type = object : TypeToken<List<RunningQuiz>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<Question?>?): String? {
        return gson.toJson(someObjects)
    }
    @TypeConverter
    fun pastQuizzesToString(pastQuizzes: List<RunningQuiz?>?): String? {
        return gson.toJson(pastQuizzes)
    }
    @TypeConverter
    fun stringToSomeStringList(data: String?): List<String?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun someStringListToString(someObjects: List<String?>?): String? {
        return gson.toJson(someObjects)
    }

}