package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.api.services.quiz.QuizAPIEntity
import com.revature.popquiz.model.room.RoomDataManager
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

    LazyRow(modifier = Modifier.fillMaxWidth())
    {
        item()
        {
            Spacer(modifier = Modifier.fillParentMaxWidth(0.05F))
            Column(modifier = Modifier.fillParentMaxWidth(0.9F))
            {
                Card(
                    shape = RoundedCornerShape(25.dp),
                    elevation = 50.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .absolutePadding(top = 10.dp)
                        .height(600.dp)
                )
                {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(fraction = 0.9F),
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
                            basicCard(
                                title = "Description:", info = quiz.longDesc
                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            // if statement to check if quiz is already in room database
                            // SQL query to check if quiz has that ID


                            UniversalButton(
                                enabled = !loaded.value,
                                text = "Download",
                                onClick = {
                                    searchQuizzesOverviewViewModel.createQuiz()
                                    Log.d("Loaded value","${loaded.value}") },
                                modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}
//    }

