package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.R
import com.revature.popquiz.model.api.services.quiz.QuizAPIEntity
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.view.shared.UniversalButton
import com.revature.popquiz.view.shared.basicCard
import com.revature.popquiz.viewmodels.SearchQuizzesOverviewViewModel

@Composable
fun QuizPreviewDownloadScreen(navController: NavController)
{
    val context= LocalContext.current

    QuizScaffold(
        sTitle = "Preview Quiz",
        navController = navController
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            QuizPreviewDownloadScreenBody(navController = NavController(context))
        }

    }
}
@Composable
fun QuizPreviewDownloadScreenBody(navController: NavController)
{
    val context= LocalContext.current
    var searchQuizzesOverviewViewModel = ViewModelProvider(context as MainActivity).get(SearchQuizzesOverviewViewModel::class.java)
    val lazyState = rememberLazyListState()


//    if (searchQuizzesOverviewViewModel.quiz != null)
//    {
    val quiz by searchQuizzesOverviewViewModel.quiz!!.observeAsState(
        QuizAPIEntity(
            title = "",
            shortDesc = "",
            longDesc = "",
            questionIDs = arrayListOf(),
            APIid = 0
        )
    )

    var loaded = RoomDataManager.quizRepository.checkExists(quiz.APIid).observeAsState(true)

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
                .padding(bottom = 50.dp)
                .background(
                    color = Color.Transparent
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        )
        {
            item()
            {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text =
                    "${quiz.title} ",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(20.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
//description


                Card(
                    modifier = Modifier.padding(10.dp),
                    elevation = 50.dp,
                    shape = RoundedCornerShape(25.dp),
                    backgroundColor = revLightOrange
                )
                {
                    Column(modifier = Modifier.padding(10.dp))
                    {
                        Text(
                            text = "Description:",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .fillMaxWidth(0.95F)
                                .padding(horizontal = 5.dp)
                        )
                        Text(
                            text = quiz.longDesc,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .padding(10.dp)
                                .fillMaxWidth(0.95F)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // if statement to check if quiz is already in room database
                // SQL query to check if quiz has that ID

                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                )
                {
                    Text(
                        text = "Are you ready to try out this quiz?",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.pngtreemultiple_orange_question_marks_clipart_5592936),
                        contentDescription = "Quiz icon",
                        Modifier
                            .size(250.dp)
                            .clip(shape = RoundedCornerShape(15.dp))
                    )
                    Text(
                        text = "Click the download button to test yourself now!",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 15.dp)
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(5.dp))
                            .absolutePadding(bottom = 5.dp),
                        onClick =
                        {
                            searchQuizzesOverviewViewModel.createQuiz()
                            Log.d("Loaded value","${loaded.value}")
                        },
                        enabled = !loaded.value,
                        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                        shape = RoundedCornerShape(25.dp)
                    )
                    {
                        Text(
                            text = "Download",
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                            fontSize = 25.sp
                        )
                    }
//                            UniversalButton(
//                                enabled = !loaded.value,
//                                text = "Download",
//                                onClick =
//                                {
//                                    searchQuizzesOverviewViewModel.createQuiz()
//                                    Log.d("Loaded value","${loaded.value}")
//                                },
//                                modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
//                            )
                }
            }
        }
    }
}
//    }

