package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun CreateQuizTitle(navController: NavController){

    Log.d("Create Q Title Screen", "Create Q Title Start")

    QuizScaffold(
        sTitle = "Quiz Title",
        navController = navController) {

        //Screen Content

    }
}