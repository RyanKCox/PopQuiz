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
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodels.SearchBarViewModel
import com.revature.popquiz.view.shared.QuizCardForLazyColumn as QuizCardForLazyColumn

@ExperimentalAnimationApi
@Composable
fun SearchQuizzesScreen(navController: NavController)
{
    //navController: NavController
    val scaffoldState = rememberScaffoldState()
    val context= LocalContext.current

    QuizScaffold(
        sTitle = "Search Quizzes",
        navController = navController
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            SearchQuizzesBody(searchBarViewModel = SearchBarViewModel())
        }

    }
}


@ExperimentalAnimationApi
@Composable
fun SearchQuizzesBody(searchBarViewModel: SearchBarViewModel)
{
    val context = LocalContext.current
    val lazyState = rememberLazyListState()

    val searchWidgetState by searchBarViewModel.searchWidgetState
    val searchTextState by searchBarViewModel.searchTextState

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


@Composable
fun quizTags()
{
    Text(
        text = "Tags: ",
        textAlign = TextAlign.Center
    )
    // Insert tags here

    val tags: Set<String>
    tags = setOf("Language", " Topic", " Quiz type")

    Text(
        text = tags.toString(),
        textAlign = TextAlign.Center
    )
}