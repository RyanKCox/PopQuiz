package com.revature.popquiz.model.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginDataStore(val context: Context) {
    companion object{
        private val Context.myDataStore: DataStore<Preferences> by preferencesDataStore("userDataFile")
        val USER_EMAIL_KEY= stringPreferencesKey("user_email") //key name to retrieve data
        val USER_PASSWORD_KEY= stringPreferencesKey("user_password") //key name to retrieve data
        val USER_NAME_KEY= stringPreferencesKey("name")//key to retrieve name
        val STAY_LOGGED_IN= stringPreferencesKey("stay_logged_in")
        val SUBSCRIBED= stringPreferencesKey("subscribed")
    }
    val getLoggedIn: Flow<String?> =context.myDataStore.data
        .map{preferences->
            preferences[STAY_LOGGED_IN]?:"FALSE" // this allows a return or a default email to be returned
        }
    val getSubsribed: Flow<String?> = context.myDataStore.data
        .map{preferences->
            preferences[SUBSCRIBED]?:"FALSE"
        }
    val getEmail: Flow<String?> =context.myDataStore.data
        .map{preferences->
            preferences[USER_EMAIL_KEY]?:"FIRSTLAST@GMAIL.COM" // this allows a return or a default email to be returned
        }
    val getPass: Flow<String?> =context.myDataStore.data
        .map{preferences->
            preferences[USER_PASSWORD_KEY]?:"PASSWORD" // this allows a return or a default password to be returned
        }
    val getName:Flow<String?> =context.myDataStore.data
        .map { preferences-> preferences[USER_NAME_KEY]?:"NAME" }

    //save email
    suspend fun saveName(name: String)
    {
        context.myDataStore.edit { preferences->
            preferences[USER_NAME_KEY]=name
        }
    }
    suspend fun saveEmail(name:String)
    {
        context.myDataStore.edit { preferences->
            preferences[USER_EMAIL_KEY]=name
        }
    }
    suspend fun savePassword(pass:String)
    {
        context.myDataStore.edit { preferences->
            preferences[USER_PASSWORD_KEY]=pass
        }
    }
    suspend fun saveLoggedIn(loggedInState:String)
    {
        context.myDataStore.edit { preferences->
            preferences[STAY_LOGGED_IN]=loggedInState
        }
    }
    suspend fun saveSubscribed(subscribed:String)
    {
        context.myDataStore.edit {preferences->
            preferences[SUBSCRIBED]=subscribed
        }
    }
}