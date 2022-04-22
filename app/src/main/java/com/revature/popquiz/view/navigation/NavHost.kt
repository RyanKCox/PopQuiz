package com.revature.popquiz.view.navigation

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.revature.popquiz.Register


import com.revature.popquiz.view.screens.createflashcard.CreateFlashcardScreen

//import androidx.hilt.navigation.compose.hiltViewModel

import com.revature.popquiz.view.screens.*
import com.revature.popquiz.view.screens.popquiz.PopQuizScreen
import com.revature.popquiz.view.screens.question.QuestionScreen


import com.revature.popquiz.view.screens.createquiz.CreateQuizQuestions
import com.revature.popquiz.view.screens.editquiz.EditQuestion
import com.revature.popquiz.view.screens.editquiz.EditQuizQuestionSelect
import com.revature.popquiz.view.screens.editquiz.EditQuizResources
import com.revature.popquiz.view.screens.editquiz.EditQuizTitle


import com.revature.popquiz.view.screens.flashcard.FlashCardScreen



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun StartNav(navController: NavHostController)
{

    Log.d("Start Nav", "Start Nav Started")
    
    NavHost(
        navController = navController,
        startDestination = NavScreens.LoginScreen.route
    )
    {
        
        //Login Screen
        composable (NavScreens.LoginScreen.route)
        {
            LoginScreen(navController = navController)
        }
        composable(NavScreens.RegistrationScreen.route)
        {
            Register(navController=navController)
        }
        
        //Create Quiz Screens
        composable(NavScreens.CreateQuizTitle.route)
        {
          //  val createQuizVM =
//                hiltViewModel<CreateQuizVM>()
            //CreateQuizVM(newQuiz = Quiz())
            CreateQuizTitle(navController = navController,
            )
        }
        composable(NavScreens.CreateQuizResources.route){
            CreateQuizResources(navController = navController)
        }
        composable(NavScreens.CreateQuizQuestions.route){
            CreateQuizQuestions(navController = navController)
        }

        //Edit Quiz Screens
        composable(NavScreens.EditQuizTitle.route){
            EditQuizTitle(navController = navController)
        }
        composable(NavScreens.EditQuizResource.route){
            EditQuizResources(navController = navController)
        }
        composable(NavScreens.EditQuizQuestionSelect.route)
        {
            EditQuizQuestionSelect(navController = navController)
        }
        composable(NavScreens.EditQuestion.route)
        {
            EditQuestion(navController = navController)
        }

        // Saved Quizzes Screen
        composable(NavScreens.SavedQuizzesScreen.route)
        {
            SavedQuizzesScreen(
                navController = navController)
        }

        // Search Quizzes Screen
        composable(NavScreens.SearchQuizzesScreen.route)
        {
            SearchQuizzesScreen(navController = navController)
        }

        // Quiz Preview Download Screen
        composable(NavScreens.QuizPreviewDownloadScreen.route)
        {
            quizPreviewDownloadScreen(navController = navController)
        }

        //Create Quizzes screens
        composable(NavScreens.CreateQuizResources.route)
        {
            CreateQuizResources(navController = navController)
        }
        composable(NavScreens.CreateQuizQuestions.route)
        {
            CreateQuizQuestions(navController = navController)
        }
        
        //Question Screen
        composable(NavScreens.QuestionScreen.route) {
            QuestionScreen(navController = navController)
        }

        //Profile Screen
        composable(NavScreens.ProfileScreen.route){
            profile(navController = navController)
        }

        //Settings Screen
        composable(NavScreens.SettingsScreen.route){
            Settings(navController = navController)
        }

        //Quiz Overview
        composable(NavScreens.QuizOverviewScreen.route){
            quizOverView(navController = navController)
        }
        
        //Create Flashcard Screen
        composable(NavScreens.CreateFlashcardScreen.route) {
            CreateFlashcardScreen(navController = navController)
        }

        //Flashcard Screen
        composable(NavScreens.FlashcardScreen.route) {
            FlashCardScreen(navController = navController)
        }

        //PopQuiz Screen
        composable(NavScreens.PopQuizScreen.route) {
            PopQuizScreen(navController = navController)
        }
    }
}
