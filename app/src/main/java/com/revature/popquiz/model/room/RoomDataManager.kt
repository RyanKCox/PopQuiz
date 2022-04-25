package com.revature.popquiz.model.room


import androidx.compose.runtime.collectAsState
import com.revature.popquiz.model.datastore.LoginDataStore
import com.revature.popquiz.model.room.profileroom.ProfileRepository
import com.revature.popquiz.model.room.quizroom.QuizRepository

object RoomDataManager {

     lateinit var quizRepository: QuizRepository
     lateinit var profileRepository: ProfileRepository

     var userEmail = ""
}