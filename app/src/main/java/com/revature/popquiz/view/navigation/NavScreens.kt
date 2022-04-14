package com.revature.popquiz.view.navigation

sealed class NavScreens(val route:String) {

    //Login
    object LoginScreen:NavScreens("LoginScreen")
    object RegistrationScreen:NavScreens("RegistrationScreen")

    //Create Quiz Screens
    object CreateQuizTitle:NavScreens("CreateQuizTitle")
    object CreateQuizResources:NavScreens("CreateQuizResources")
    object CreateQuizQuestions:NavScreens("CreateQuizQuestions")

    // Saved Quizzes Screeen
    object SavedQuizzesScreen: NavScreens("SavedQuizzesScreen")

    // Search Quizzes Screen
    object SearchQuizzesScreen: NavScreens("SearchQuizzesScreen")

    //Quiz Question Screen
    object QuestionScreen: NavScreens("QuestionScreen")

    //Flashcard Screen
    object FlashCardScreen: NavScreens("FlashCardScreen")
}