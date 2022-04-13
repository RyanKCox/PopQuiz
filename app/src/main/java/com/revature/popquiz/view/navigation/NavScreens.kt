package com.revature.popquiz.view.navigation

sealed class NavScreens(val route:String) {

    //Login
    object LoginScreen:NavScreens("LoginScreen")
    object RegistrationScreen:NavScreens("RegistrationScreen")

    //Create Quiz Screens
    object CreateQuizTitle:NavScreens("CreateQuizTitle")
}