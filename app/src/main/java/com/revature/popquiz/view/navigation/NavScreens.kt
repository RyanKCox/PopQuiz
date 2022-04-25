package com.revature.popquiz.view.navigation

sealed class NavScreens(val route:String)
{

    //Login
    object LoginScreen:NavScreens("LoginScreen")
    object RegistrationScreen:NavScreens("RegistrationScreen")

    //Create Quiz Screens
    object CreateQuizTitle:NavScreens("CreateQuizTitle")
    object CreateQuizResources:NavScreens("CreateQuizResources")
    object CreateQuizQuestions:NavScreens("CreateQuizQuestions")

    //Edit Quiz Screens
    object EditQuizTitle:NavScreens("EditQuizTitle")
    object EditQuizResource:NavScreens("EditQuizResource")
    object EditQuizQuestionSelect:NavScreens("EditQuizQuestionSelect")
    object EditQuestion:NavScreens("EditQuestion")

    // Saved Quizzes Screen
    object SavedQuizzesScreen: NavScreens("SavedQuizzesScreen")

    // Search Quizzes Screen
    object SearchQuizzesScreen: NavScreens("SearchQuizzesScreen")

    // Quiz Preview Download Screen
    object QuizPreviewDownloadScreen: NavScreens("QuizPreviewDownloadScreen")

    //Quiz Question Screen
    object QuestionScreen: NavScreens("QuestionScreen")



    //Flashcard Screen
    object FlashcardScreen: NavScreens("FlashcardScreen")

    //CreateFlashcardScreen
    object CreateFlashcardScreen: NavScreens("CreateFlashCardScreen")

    object PopQuizActivity: NavScreens("PopQuizActivity")

    //PopQuiz Screen
    object PopQuizScreen: NavScreens("PopQuizScreen")

    //PopQuizSettings Screen
    object PopQuizSettingsScreen: NavScreens("PopQuizSettingsScreen")

    //Profile Screen
    object ProfileScreen:NavScreens("ProfileScreen")

    //Settings Screen
    object SettingsScreen:NavScreens("SettingsScreen")

    //Quiz Overview
    object QuizOverviewScreen:NavScreens("QuizOverviewScreen")

    //SearchQuizOverview
    object SearchQuizOverview:NavScreens("SearchQuizOverviewScreen")

    //QuizFinish Screen
    object QuizFinishScreen:NavScreens("QuizFinishScreen")

    //Review Quiz Screen
    object ReviewQuizScreen:NavScreens("ReviewQuizScreen")


}