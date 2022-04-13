package com.revature.popquiz.view.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.revature.popquiz.view.screens.CreateQuizTitle
import com.revature.popquiz.view.screens.LoginScreen

@Composable
fun StartNav(navController: NavHostController){

    Log.d("Start Nav", "Start Nav Started")
    
    NavHost(
        navController = navController,
        startDestination = NavScreens.LoginScreen.route
    ){
        
        //Login Screen
        composable (NavScreens.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        
        //Create Quiz Screens
        composable(NavScreens.CreateQuizTitle.route){
            CreateQuizTitle(navController = navController)
        }
    }
}