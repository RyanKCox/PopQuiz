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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavController
import com.revature.popquiz.model.dataobjects.Question
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
    var questionType by remember { mutableStateOf<Int>(0)}

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxSize(.95f)
                .padding(15.dp),
            shape = RoundedCornerShape(40.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                questionType = QuestionTypeDropDown()

                Spacer(Modifier.size(40.dp))

                when(questionType){
                    Question.QUESTION_TYPE_TRUE_FALSE->{

                        TrueFalseQuestion()
                    }
                    Question.QUESTION_TYPE_SINGLE_ANSWER->{

                        SingleAnswerQuestion()
                    }
                    Question.QUESTION_TYPE_MULTI_ANSWER->{

                        MultiAnswerQuestion()
                    }
                }

                //Text Field for Writing the question
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f),
                    value = sQuestionTitle,
                    onValueChange = {sQuestionTitle = it},
                    label = { Text("Question?",
                        style = MaterialTheme.typography.h6) },
                    maxLines = 2
                )

                Spacer(Modifier.size(40.dp))

                //
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f),
                    value = sAnswer,
                    onValueChange = {sAnswer = it},
                    label = { Text("Answer?",
                            style = MaterialTheme.typography.h6) },
                    maxLines = 2
                )

                Spacer(Modifier.size(40.dp))
                WrongAnswersView(context = context)

            }
        }
    }
}
@Composable
fun TrueFalseQuestion(){

}
@Composable
fun SingleAnswerQuestion(){

}
@Composable
fun MultiAnswerQuestion(){

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
            style = MaterialTheme.typography.h6)
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

@Composable
fun QuestionTypeDropDown():Int{
    var mExpanded by remember { mutableStateOf(false)}
    val types = listOf("True/False","Single-Answer","Multi-Answer")
    var selectedType by remember { mutableStateOf(types.get(0))}
    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    //Icons for expanding
    val icon = if(mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)){

        OutlinedTextField(
            value = selectedType,
            onValueChange = {selectedType = it},
            modifier = Modifier
                .fillMaxWidth(.8f)
                .onGloballyPositioned {
                    textFieldSize = it.size.toSize()
                },
            label = {Text("Question Type")},
            trailingIcon = {
                Icon(icon,"DropDown",
                    Modifier.clickable { mExpanded = !mExpanded }
                )
            }
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = {mExpanded = false},
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ){
            for (type in types) {
                DropdownMenuItem(
                    onClick = {
                        selectedType = type
                        mExpanded = false
                    }) {
                    Text(type)
                }

            }
        }
    }
    var typeID:Int = 0
    when (selectedType){

        types.get(0) -> typeID = Question.QUESTION_TYPE_TRUE_FALSE
        types.get(1) -> typeID = Question.QUESTION_TYPE_SINGLE_ANSWER
        types.get(2) -> typeID = Question.QUESTION_TYPE_MULTI_ANSWER
    }
    return typeID
}

@Preview
@Composable
fun CQQPreview(){
    CreateQuestQuestionsBody()
}