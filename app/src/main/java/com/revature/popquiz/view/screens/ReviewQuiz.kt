package com.revature.popquiz.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.ui.theme.revOrange

@Composable
fun reviewQuiz() {
    val quiz = Quiz()
    quiz.title="Arrays"
    quiz.score=90
    quiz.longDescription="This quiz reviews the basics of arrays in Kotlin." +
            " This includes properties of arrays and their usage"
    quiz.tagList.add(0,"Index")
    quiz.tagList.add(1,"Arrays")
    val answer1=Answer("A programming Language",true)
    val answer2=Answer("Easy",false)
    val answerList= listOf<Answer>(answer1,answer2)
    val q=Question(1,"What is Kotlin?",answerList)
    quiz.questionList.add(0,q)


    Scaffold(
        backgroundColor = revBlue,
        topBar = {
            TopAppBar(backgroundColor = revOrange) {

            }
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        )
        {
            Card(
                shape = RoundedCornerShape(25.dp),
                elevation = 50.dp,
                modifier = Modifier
                    .fillMaxSize(0.9F)

            ){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${quiz.title} Quiz",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            items(quiz.questionList){question->
                                Card(modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(10.dp),
                                    shape = RoundedCornerShape(25.dp), backgroundColor = revLightOrange) {
                                    Column (modifier = Modifier.padding(10.dp)){
                                        Text(text = question.question,fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium)
                                        question.answers.forEach {
                                            answers(answer = it)

                                        }
                                    }

                                }
                            }
                            item{
                                Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                    revOrange)) {
                                    Text(text = "Done")
                                }
                            }
                        }



                }


            }
        }
    }

}

//-----------------------------------------Composables and preview---------------------//

@Composable
fun answers(answer: Answer)
{
    Card(modifier = Modifier
        .fillMaxWidth(0.9F)
        .padding(5.dp),
        backgroundColor = if (answer.bCorrect)Color.Green else Color.White) {
        Text(text = answer.sAnswer, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())

    }
}

@Composable
@Preview
fun reviewPreview()
{
    reviewQuiz()
}