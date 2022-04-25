package com.revature.popquiz.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.ui.theme.Teal200
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.screens.question.QuestionViewModel
import com.revature.popquiz.view.shared.QuizScaffold


@Composable
fun quizComplete(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val questionVM =
        ViewModelProvider(context as MainActivity)
            .get(QuestionViewModel::class.java)
    val quiz=questionVM.runningQuiz

    QuizScaffold(
        sTitle = "QUIZ COMPLETE",
        navController = navController
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(fraction = 0.9F),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    )
                    {
                        Text(
                            text = questionVM.quiz.title.uppercase(),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(20.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))

                        Card(
                            backgroundColor = revOrange,
                            shape = RoundedCornerShape(25.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                                .clickable { },
                            elevation = 10.dp
                        ) {
                            Column(
                            // modifier = Modifier.padding(15.dp)
                            ) {
                                Text(
                                    ("Your Score is: ${quiz.finalScore}%")
                                )

                            }
                        }

                            reviewAnswersButton(navController)
                            exitButton()

                    }

                }

            }
    }
}

@Composable
fun reviewAnswersButton(navController: NavController) {
    Button(modifier = Modifier
        .padding(10.dp)
        .height(100.dp)
        .width(150.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(25),
        onClick = {

            navController.navigate(NavScreens.ReviewQuizScreen.route)
        })
    {
        Text(
            text = "Review Answers",
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun shareButton(/*navController: NavController*/) {
    Button(modifier = Modifier
        .padding(10.dp)
        .height(100.dp)
        .width(150.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(25),
        onClick = {


        })
    {
        Text(
            text = "Share With Friends",
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun exitButton(/*navController: NavController*/) {
    Button(modifier = Modifier
        .padding(10.dp)
        .height(100.dp)
        .width(150.dp)
        .wrapContentHeight(),
        shape = RoundedCornerShape(25),
        onClick = {

        })
    {
        Text(
            text = "Exit",
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
fun quizFinishedScreen(navController: NavController) {
    val context = LocalContext.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
            )
            {

                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Surface()
                    {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        )
                        {
                            //quizComplete()
                            reviewAnswersButton(navController = navController)
                            exitButton()

                        }

                    }
                }
            }
        }

@Preview
@Composable
fun prevA(){
    val navController= rememberNavController()
    quizComplete(navController)
}
