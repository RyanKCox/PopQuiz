package com.revature.popquiz.view.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun CreateQuizQuestions(navController: NavController){

    QuizScaffold(
        sTitle = "Add Questions",
        navController = navController
    ) {
        CreateQuestQuestionsBody()
    }
}
@Composable
fun CreateQuestQuestionsBody(){

    val context = LocalContext.current

    var sQuestionTitle by remember { mutableStateOf("") }
    var sAnswer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize(.8f)
                .padding(15.dp),
            shape = RoundedCornerShape(40.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.size(40.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(50.dp),
                    value = sQuestionTitle,
                    onValueChange = {sQuestionTitle = it},
                    label = { Text("Question?") },
                    maxLines = 2
                )
                Spacer(Modifier.size(40.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(50.dp),
                    value = sAnswer,
                    onValueChange = {sAnswer = it},
                    label = { Text("Answer?") },
                    maxLines = 2
                )

                Spacer(Modifier.size(40.dp))
                WrongAnswersView(context = context)

            }
        }
    }
}
@Composable
fun WrongAnswersView(context: Context){

    var sWrong by remember { mutableStateOf("") }
    val wrongList:ArrayList<String> = arrayListOf()

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sWrong,
        onValueChange = {sWrong = it},
        label = { Text(
            "Wrong Answers",
            style = MaterialTheme.typography.h5)
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add Wrong Answer Icon",
                modifier = Modifier
                    .clickable {
                        if (sWrong != "" || wrongList.size <= 3) {
                            wrongList.add(sWrong)
                            sWrong = ""
                        }
                        else if (wrongList.size > 3){
                            Toast.makeText(
                                context,
                                "Only 3 wrong answers allowed",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        else{
                            Toast.makeText(
                                context,
                                "Nothing in Wrong Answer Field",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            )
        }
    )

    Spacer(Modifier.size(20.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth(.8f)
            .border(2.dp, Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Dummy Data
        wrongList.add("Kotlin")
        wrongList.add("Java")
        wrongList.add("Context")
        wrongList.add("Testing")
        wrongList.add("Services")


        Spacer(Modifier.size(10.dp))

        for (topic in wrongList) {

            Text(
                text = topic,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(Modifier.size(10.dp))

    }
}

@Preview
@Composable
fun CQQPreview(){
    CreateQuestQuestionsBody()
}