package com.revature.popquiz.view.screens


import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar.quizBarSearch
import com.revature.popquiz.MainActivity

import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizCardForLazyColumn
import com.revature.popquiz.view.shared.QuizScaffold


import com.revature.popquiz.viewmodel.QuizOverviewVM
import com.revature.popquiz.viewmodel.SavedQuizVM


@ExperimentalAnimationApi
@Composable
fun SavedQuizzesScreen(navController: NavController)
{
    QuizScaffold(
        sTitle = "Saved Quizzes",
        navController = navController
    )
    {
        SavedQuizzesBody(navController)
    }

}


@ExperimentalAnimationApi
@Composable
fun SavedQuizzesBody(navController: NavController)
{
    var context = LocalContext.current

    //ViewModels for screen
    var savedQuizVM = ViewModelProvider(context as MainActivity).get(SavedQuizVM::class.java)
    var quizOverviewVM = ViewModelProvider(context as MainActivity).get(QuizOverviewVM::class.java)

    //Search functionality variables
    var x = savedQuizVM.sSearchValue
    var sSearchValue by remember { mutableStateOf(x) }

    //Lazy column state
    val lazyState = rememberLazyListState()

    //Alert Dialog display
    var showDialog by remember { mutableStateOf(false)}

    //Sort List by Search value
    savedQuizVM.sortBySearch()

    //Observe the list of quizzes
    val quizList by savedQuizVM.sortedList.observeAsState(listOf())

    //Alert dialog for deleting question
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false

            },
            title = {
                Text("Delete Question?")
            },
            text = {
                Text("Do you want to delete this question?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        if(savedQuizVM.nDeleteID != null) {
                            savedQuizVM.deleteQuiz()
                            savedQuizVM.nDeleteID = null
                        }
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Spacer(Modifier.size(10.dp))
        Card(
            modifier = Modifier
                .fillMaxSize()
                .absolutePadding(
                    top = 5.dp,
                ),
            shape = AbsoluteRoundedCornerShape(
                topLeft = 20.dp,
                topRight = 20.dp
            ),
            elevation = 10.dp
        )
        {
            LazyColumn(
                state = lazyState,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.Transparent
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            )
            {
                //Search Bar
                item()
                {
                    quizBarSearch()
                    Log.d("Search bar", sSearchValue)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                //Quiz List display
                items(quizList)
                { Quiz ->

                    Card(
                        modifier =
                        Modifier
                            .clickable {

                                //Set the quiz for display in Overview VM
                                quizOverviewVM.quiz = Quiz

                                navController.navigate(NavScreens.QuizOverviewScreen.route)
                            }
                            .height(150.dp)
                            .fillMaxWidth()
                            .absolutePadding(bottom = 10.dp)
                            .padding(horizontal = 5.dp),

                        shape = RoundedCornerShape(25.dp),
                        elevation = 10.dp
                    )
                    {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.absolutePadding(top = 10.dp)
                        )
                        {
                            //Quiz Title Text
                            Text(
                                text = Quiz.title,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            //Quiz short Description text
                            Text(
                                text = Quiz.shortDescription,
                                textAlign = TextAlign.Center
                            )
                        }

//                        Row(
//                            verticalAlignment = Alignment.Bottom,
//                            horizontalArrangement = Arrangement.Center,
//                            modifier = Modifier.absolutePadding(bottom = 10.dp)
//                        )
//                        {
//                            //Quiz Tags
//                            quizTags()
//                        }

                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                Image(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Remove Quiz Icon",
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clickable {
                                            showDialog = true
                                            savedQuizVM.nDeleteID = Quiz.id

                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
}



