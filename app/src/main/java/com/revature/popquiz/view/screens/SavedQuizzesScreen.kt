package com.revature.popquiz.view.screens


import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.androiddevchallenge.presentation.searchbarcomponents.searchbar.quizBarSearch
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.room.RoomDataManager.quizRepository

import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizCardForLazyColumn
import com.revature.popquiz.view.shared.QuizScaffold


import com.revature.popquiz.model.room.quizroom.QuizEntity
import com.revature.popquiz.viewmodel.QuizOverviewVM
import com.revature.popquiz.viewmodel.SavedQuizVM
import com.revature.popquiz.viewmodels.QuizManager
import com.revature.popquiz.viewmodels.SearchBarViewModel


@ExperimentalAnimationApi
@Composable
fun SavedQuizzesScreen(navController: NavController)
{
    val scaffoldState = rememberScaffoldState()
    val context= LocalContext.current

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
//    var searchBarViewModel = ViewModelProvider(context as MainActivity).get(SearchBarViewModel::class.java)
    var savedQuizVM = ViewModelProvider(context as MainActivity).get(SavedQuizVM::class.java)
    var x = savedQuizVM.sSearchValue

    var quizOverviewVM = ViewModelProvider(context as MainActivity).get(QuizOverviewVM::class.java)

    var sSearchValue by remember { mutableStateOf(x) }

    val lazyState = rememberLazyListState()

    savedQuizVM.sortBySearch()

    //val quizList= remember{ mutableStateOf(searchBarViewModel.sortedList) }

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
                item()
                {
                    quizBarSearch()
                    Log.d("Search bar", "$sSearchValue")
                }
                item()
                {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(savedQuizVM.sortedList)
                { Quiz ->
                    QuizCardForLazyColumn(
                        quizTitleText = Quiz.title,
                        shortQuizDescriptionText = Quiz.shortDescription
                    )
                    {
                        quizOverviewVM.quiz=Quiz
                        navController.navigate(NavScreens.QuizOverviewScreen.route)
                    }
                }

//                item {
////                item {
////                    Text(text = "Wow here's the value: ${searchBarViewModel.sSearchValue}")
////
////                }
            }
        }
    }
}





