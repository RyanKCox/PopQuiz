package com.revature.popquiz.model.room

//import androidx.room.ProvidedTypeConverter
//import androidx.room.TypeConverter
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.revature.popquiz.model.dataobjects.Question
//
//@ProvidedTypeConverter
//class DataConverter {
//    @TypeConverter
//    fun fromString(value:String?): MutableList<String>
//    {
//        val listType =object :TypeToken<ArrayList<String>>(){}.type
//        return Gson().fromJson(value,listType)
//    }
//    @TypeConverter
//    fun fromList(list: MutableList<String?>): String
//    {
//        val type=object :TypeToken<MutableList<Question>>(){}.type
//      return Gson().toJson(list,type)
//    }
//    @TypeConverter
//    fun fromQuestions(value : MutableList<Question> ):String
//    {
//        val type=object :TypeToken<MutableList<Question>>(){}.type
//        return Gson().toJson(value)
//    }
//    @TypeConverter
//    fun toQuestions(questions: String):MutableList<Question>
//    {
//        val type=object :TypeToken<MutableList<Question>>(){}.type
//        return Gson().fromJson(questions,type)
//    }
//
//}