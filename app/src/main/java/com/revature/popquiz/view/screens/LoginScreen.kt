package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun LoginScreen(navController: NavController){


    //Dummy Setup

    Log.d("Login Screen", "Login Screen Start")

    //Shared Scaffold - May not use in this screen
    QuizScaffold(
        sTitle = "Login",
        navController = navController) {

        //Screen Content

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            //Button to navigate
            Button(
                onClick = {

                    //dummy navigation
                    navController.navigate(NavScreens.CreateQuizTitle.route)

                }) {
                Text(text = "Login")

            }

        }

    }

}