package com.revature.popquiz.view.screens.question

import android.widget.ProgressBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.ui.theme.PopQuizTheme
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.view.screens.answers
import com.revature.popquiz.view.shared.QuizScaffold
import java.util.*

@Composable
fun QuestionScreen(navController: NavController) {
    val context= LocalContext.current
    val questionVM =
        ViewModelProvider(context as MainActivity)
            .get(QuestionViewModel::class.java)

    val scaffoldState = rememberScaffoldState()
    val quiz = questionVM.quiz
    QuizScaffold(
        sTitle = quiz.title.uppercase(),
        navController = navController,
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                ProgressBar()
                QuestionCard(quiz)
            }
        }
    )
}

@Composable
fun ProgressBar() {
    Box(modifier = Modifier
        .fillMaxHeight(0.1f)
        .fillMaxWidth()
        .padding(10.dp)) {

    }
    val progressBar: ProgressBar
}

@Composable
fun QuestionCard(quiz: Quiz) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        quiz.questionList.forEach { question->
            Card(modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(10.dp),
                shape = RoundedCornerShape(25.dp), backgroundColor = revLightOrange
            ) {
                Column (modifier = Modifier.padding(10.dp)){
                    Text(text = question.question,fontSize = 18.sp,
                        fontWeight = FontWeight.Medium)
                    question.answers.forEach {
                        answerCard(answer = it)

                    }
                }

            }

        }
        SubmitButton()
    }
}

@Composable
fun AnswerButton(answer: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(10.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
    ) {
        Text(text = answer)
    }
//    UniversalButton(
//        enabled = true,
//        text = answer,
//        onClick = { /*TODO*/ },
//        modifier = Modifier.fillMaxWidth(0.8f).padding(10.dp)
//    )
}

@Composable
fun SubmitButton() {
    Box(
        modifier = Modifier
            .height(25.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(10.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
        ) {
            Text(text = "Submit")
        }
    }
}

//@Preview()
//@Composable
//fun QuestionScreenPreview() {
//    val navController = rememberNavController()
//    PopQuizTheme {
//        QuestionScreen(navController = navController)
//    }
//}

@Composable
fun answerCard(answer: Answer)
{

    var selectedColor by remember{mutableStateOf(Color.White)}
    Card(modifier = Modifier
        .fillMaxWidth(0.9F)
        .padding(5.dp)
        .clickable { selectedColor=Color.Gray
                   answer.selected=true},
        backgroundColor = selectedColor) {
        Text(text = answer.sAnswer, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())

    }
}