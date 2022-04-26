package com.revature.popquiz.model.room


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.LiveData
import com.revature.popquiz.model.datastore.LoginDataStore
import com.revature.popquiz.model.room.profileroom.ProfileEntity
import com.revature.popquiz.model.room.profileroom.ProfileRepository
import com.revature.popquiz.model.room.quizroom.QuizRepository

object RoomDataManager
{

     lateinit var quizRepository: QuizRepository
     lateinit var profileRepository: ProfileRepository

     lateinit var userEmail : State<String?>

    lateinit var profile : LiveData<ProfileEntity>
    fun SetProfile(){
        profile = profileRepository.fetchProfileWithEmail(userEmail.value!!)

    }
}