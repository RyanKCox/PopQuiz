package com.revature.popquiz.model.room.profileroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.revature.popquiz.model.dataobjects.Quiz

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile WHERE email=:email")
    fun fetchProfileWithEmail(email:String):LiveData<ProfileEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(profile: ProfileEntity)
}