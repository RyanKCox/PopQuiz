package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar.MainSearchBar
import com.revature.popquiz.model.dataobjects.SearchWidgetState
import com.revature.popquiz.view.shared.QuizCardForLazyColumn
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodels.SearchBarViewModel


@ExperimentalAnimationApi
@Composable
fun SavedQuizzesScreen(navController: NavController, searchBarViewModel: SearchBarViewModel)
{
    // Search Bar View Model
    val searchWidgetState by searchBarViewModel.searchTextState
    val searchTextState by searchBarViewModel.searchTextState

    // navController: NavController
    val scaffoldState = rememberScaffoldState()
    val context= LocalContext.current

    QuizScaffold(
        sTitle = "Saved Quizzes",
        navController = navController
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            SavedQuizzesBody(searchBarViewModel = SearchBarViewModel())
        }

    }
}

@ExperimentalAnimationApi
@Composable
fun SavedQuizzesBody(searchBarViewModel: SearchBarViewModel)
{
    // Search Bar View Model
    val searchWidgetState by searchBarViewModel.searchWidgetState
    val searchTextState by searchBarViewModel.searchTextState


    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    Surface(
        // Border for screen (shape + colors/gradient)
    )
    {
        Surface(
            // Background Image goes here
        )
        {
            // Search bar goes here



            LazyColumn(

                state = lazyState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp)
                    .background(
                        color = Color.Transparent
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                item {
                    MainSearchBar(
                        searchWidgetState = searchWidgetState,
                        searchTextState = searchTextState,
                        onTextChange =
                        {
                            searchBarViewModel.updateSearchTextState(newValue = it)
                        },
                        onCloseClicked =
                        {
                            searchBarViewModel.updateSearchTextState(newValue = "")
                            searchBarViewModel.updateSearchWidgetState(newValue = SearchWidgetState.CLOSED)
                        },
                        onSearchClicked =
                        {
                            Log.d("Searched Text", it)
                        },
                        onSearchTriggered =
                        {
                            searchBarViewModel.updateSearchWidgetState(newValue = SearchWidgetState.OPENED)
                        },
                    )
                }


                item{
                    QuizCardForLazyColumn(
                        quizTitleText = "Java Basics",
                        shortQuizDescriptionText = "Short quiz description"
                    )
                }

                item{
                    QuizCardForLazyColumn(
                        quizTitleText = "Kotlin Fundamentals",
                        shortQuizDescriptionText = "Short quiz description"
                    )
                }

                item{
                    QuizCardForLazyColumn(
                        quizTitleText = "Intro to Databases",
                        shortQuizDescriptionText = "Short quiz description"
                    )
                }

                item{
                    QuizCardForLazyColumn(
                        quizTitleText = "MVVM Design Pattern",
                        shortQuizDescriptionText = "Short quiz description"
                    )
                }


                item{
                    QuizCardForLazyColumn(
                        quizTitleText = "Quiz Title",
                        shortQuizDescriptionText = "Short quiz description"
                    )
                }


                item{
                    QuizCardForLazyColumn(
                        quizTitleText = "Quiz Title",
                        shortQuizDescriptionText = "Short quiz description"
                    )
                }
            }
        }
    }
}


