package com.revature.popquiz.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.revature.popquiz.model.dataobjects.Quiz
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class QuizOverviewVM @Inject constructor():ViewModel(), LifecycleObserver {

    var quiz:Quiz?=null

    fun loadWebpage(context: Context,url: String){

        val intent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(url)
        }
        try{
            context.startActivity(intent)

        } catch (e:Exception){
            Log.d("Resource","Intent launch failed: ${e.message}")
        }

    }

}