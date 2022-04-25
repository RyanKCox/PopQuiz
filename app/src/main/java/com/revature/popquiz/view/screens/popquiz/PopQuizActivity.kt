package com.revature.popquiz.view.screens.popquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

class PopQuizActivity(navController: NavController): ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            PopQuizScreen(navController = navController)
        }

    }
}
//    @Composable
//    fun PopQuizScreen(navController: NavController) {
//
//        QuizScaffold(
//            sTitle = "Pop! Quiz",
//            navController = navController,
//            content = {
//                Column(
//                    modifier = Modifier.fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally,
//                    verticalArrangement = Arrangement.Top
//                ) {
//                    Spacer(Modifier.size(10.dp))
//                    Card(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .absolutePadding(
//                                top = 5.dp,
//                            ),
//                        shape = AbsoluteRoundedCornerShape(
//                            topLeft = 20.dp,
//                            topRight = 20.dp
//                        ),
//                        elevation = 10.dp
//                    ) {
//                        ProgressBar()
//                        QuestionCard()
//                        SubmitButton()
//                    }
//                }
//            }
//        )
//    }
//
//    @Composable
//    fun ProgressBar() {
//        Box(modifier = Modifier
//            .fillMaxHeight(0.1f)
//            .fillMaxWidth()
//            .padding(10.dp)
//        ) {
//            Text(
//                text = "Pop! Quiz Time!",
//                modifier = Modifier,
//                style = TextStyle(Color.Black),
//                textAlign = TextAlign.Center,
//                fontWeight = FontWeight.Bold,
//                fontSize = 50.sp
//            )
//        }
//    }
//
//    @Composable
//    fun QuestionCard() {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//        ) {
//            Card(
//                shape = RoundedCornerShape(25.dp),
//                modifier = Modifier
//                    .fillMaxHeight(0.8f)
//                    .fillMaxWidth()
//                    .padding(25.dp),
//                elevation = 20.dp
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize(0.9f),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Spacer(modifier = Modifier.height(40.dp))
//                    Text(text = "Question #", color = Color.Black)
//                    Spacer(modifier = Modifier.height(20.dp))
//                    Text(text = "This is an example of a question?", color = Color.Black)
//                    Spacer(modifier = Modifier.height(50.dp))
//                    AnswerButton(answer = "Answer 1")
//                    Spacer(modifier = Modifier.height(10.dp))
//                    AnswerButton(answer = "Answer 2")
//                    Spacer(modifier = Modifier.height(10.dp))
//                    AnswerButton(answer = "Answer 3")
//                    Spacer(modifier = Modifier.height(10.dp))
//                    AnswerButton(answer = "Answer 4")
//                }
//            }
//        }
//    }
//
//    @Composable
//    fun AnswerButton(answer: String) {
//        Button(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .fillMaxWidth(0.8f)
//                .padding(10.dp),
//            shape = RoundedCornerShape(25.dp),
//            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
//        ) {
//            Text(text = answer)
//        }
////    UniversalButton(
////        enabled = true,
////        text = answer,
////        onClick = { /*TODO*/ },
////        modifier = Modifier.fillMaxWidth(0.8f).padding(10.dp)
////    )
//    }
//
//    @Composable
//    fun SubmitButton() {
//        Box(
//            modifier = Modifier
//                .height(25.dp)
//                .fillMaxWidth(),
//            contentAlignment = Alignment.BottomCenter,
//        ) {
//            Button(
//                onClick = { /*TODO*/ },
//                modifier = Modifier
//                    .fillMaxWidth(0.7f)
//                    .padding(10.dp),
//                shape = RoundedCornerShape(25.dp),
//                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
//            ) {
//                Text(text = "Submit")
//            }
//        }
//    }

//@PreviewParameter([ provider = NavController])
//@Composable
//fun QuestionScreenPreview(navController: NavController) {
//    PopQuizTheme {
//        QuestionScreen(navController = navController)
//    }
//}
