package com.revature.popquiz.model.room.profileroom

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.room.AppDataBase

class ProfileRepository(application: Application) {
    private var profileDao: ProfileDao

    init {
        var dataBase= AppDataBase.getDataBase(application)
        profileDao=dataBase.profileDao()
    }

    suspend fun insertProfile(profile: ProfileEntity){
        profileDao.insertProfile(profile)
    }

    fun fetchProfileWithEmail(email:String):LiveData<ProfileEntity> {
        return profileDao.fetchProfileWithEmail(email)
    }
}