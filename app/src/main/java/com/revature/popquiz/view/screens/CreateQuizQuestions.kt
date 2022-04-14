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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.CreateQuizVM

@Composable
fun CreateQuizQuestions(navController: NavController){

    QuizScaffold(
        sTitle = "Add Questions",
        navController = navController
    ) {
        CreateQuestQuestionsBody(navController)
    }
}
@Composable
fun CreateQuestQuestionsBody(navController: NavController){

    val context = LocalContext.current
    val createQuizVM = ViewModelProvider(context as MainActivity).get(CreateQuizVM::class.java)


    var sQuestionTitle by remember { mutableStateOf("") }
//    var sAnswer by remember { mutableStateOf("") }
    var questionType by remember { mutableStateOf(0)}
    var answerList:List<Answer> by remember { mutableStateOf(listOf())}

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

                Spacer(Modifier.size(10.dp))

                questionType = QuestionTypeDropDown(questionType)

                Spacer(Modifier.size(10.dp))

                sQuestionTitle = GetQuestionTitle()

                Spacer(Modifier.size(10.dp))

                when(questionType){
                    Question.QUESTION_TYPE_TRUE_FALSE->{
                        answerList = TrueFalseQuestion()
                    }
                    Question.QUESTION_TYPE_SINGLE_ANSWER->{

                        answerList = QuestionAnswers(context)
                    }
                    Question.QUESTION_TYPE_MULTI_ANSWER->{

                        answerList = QuestionAnswers(context)
                    }
                }

                //Button for Done
                Button(
                    onClick = {
                        if(sQuestionTitle == ""){
                            Toast.makeText(context,"Question Field must be filled out",Toast.LENGTH_LONG).show()

                        } else if(questionType == Question.QUESTION_TYPE_SINGLE_ANSWER){

                            var nCount = 0
                            answerList.forEach { answer ->
                                if (answer.bCorrect)
                                    nCount++

                            }

                            if (nCount != 1){
                                Toast.makeText(context,"Must have 1 correct answer",Toast.LENGTH_LONG).show()
                            } else{
                                //Save and navigate
                                createQuizVM.newQuiz.questionList.add(Question(
                                    nType = questionType,
                                    question = sQuestionTitle,
                                    answers = answerList
                                ))
                                //Add to Room/API

                                //Navigate
//                                navController.navigate(NavScreens.SavedQuizzesScreen.route)
                                navController.popBackStack(NavScreens.CreateQuizTitle.route,true)
                            }

                        } else if (questionType == Question.QUESTION_TYPE_MULTI_ANSWER){
                            var bHasAnswer = false
                            answerList.forEach { answer->
                                if (answer.bCorrect){
                                    bHasAnswer = true
                                }

                            }
                            if (!bHasAnswer){
                                Toast.makeText(context,"Atleast one answer has to be correct",Toast.LENGTH_LONG).show()
                            } else {
                                //save and navigate
                                //Save and navigate
                                createQuizVM.newQuiz.questionList.add(Question(
                                    nType = questionType,
                                    question = sQuestionTitle,
                                    answers = answerList
                                ))
                                //Add to Room/API

                                //Navigate
//                                navController.navigate(NavScreens.SavedQuizzesScreen.route)
                                navController.popBackStack(NavScreens.CreateQuizTitle.route,true)

                            }
                        }  else if(questionType == Question.QUESTION_TYPE_TRUE_FALSE){
                            //save and navigate
                            //Save and navigate
                            createQuizVM.newQuiz.questionList.add(Question(
                                nType = questionType,
                                question = sQuestionTitle,
                                answers = answerList
                            ))
                            //Add to Room/API

                            //Navigate
//                            navController.navigate(NavScreens.SavedQuizzesScreen.route)
                            navController.popBackStack(NavScreens.CreateQuizTitle.route,true)

                        }
                    }
                ) {
                    Text(text = "Finish",style = MaterialTheme.typography.body1)

                }

//                WrongAnswersView(context = context)

            }
        }
    }
}
@Composable
fun TrueFalseQuestion():List<Answer> {

    var answerList by remember {mutableStateOf( arrayListOf<Answer>())}

    var trueCheck by remember { mutableStateOf(true) }
    var falseCheck by remember { mutableStateOf(false) }
    answerList.add(Answer("True",trueCheck))
    answerList.add(Answer("False",falseCheck))


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){

        //True Check
        Checkbox(
            checked = trueCheck,
            onCheckedChange = {
                trueCheck = !trueCheck
                falseCheck = !trueCheck
                answerList[0].bCorrect = trueCheck
                answerList[1].bCorrect = falseCheck
            }
        )
        Text(text = "True", style = MaterialTheme.typography.body1)

        //False Check
        Checkbox(
            checked = falseCheck,
            onCheckedChange = {
                falseCheck = !falseCheck
                trueCheck = !falseCheck
                answerList[0].bCorrect = trueCheck
                answerList[1].bCorrect = falseCheck
            }
        )
        Text(text = "False", style = MaterialTheme.typography.body1)


    }
    return answerList

}
@Composable
fun QuestionAnswers(context:Context):List<Answer>{

    var sAnswer by remember { mutableStateOf("")}
    var answerList by remember {mutableStateOf( arrayListOf<Answer>())}


    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sAnswer,
        onValueChange = {sAnswer = it},
        maxLines = 2,
        label = { Text(
            "Answers",
            style = MaterialTheme.typography.h6)
        },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add Wrong Answer Icon",
                modifier = Modifier
                    .clickable {
                        if (sAnswer.isNotEmpty() || answerList.size <= 3) {
                            answerList.add(Answer(sAnswer,false))
                            sAnswer = ""
                        }
                        else if (answerList.size > 3){
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
            .border(2.dp, Color.Gray)
            .height(200.dp),
    ) {

        Spacer(Modifier.size(10.dp))

        for (answer in answerList) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp)) {

                Checkbox(
                    checked = answer.bCorrect,
                    onCheckedChange = {
                        answer.bCorrect = !answer.bCorrect
                    }
                )
                Text(
                    text = answer.sAnswer,
                    style = MaterialTheme.typography.body2,
                    maxLines = 2
                )
            }
        }
    }
    return answerList.toList()
}
@Composable
fun MultiAnswerQuestion(){

}

@Composable
fun GetQuestionTitle():String{

    var sTitle by remember { mutableStateOf("") }

    //Text Field for Writing the question
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(.8f),
        value = sTitle,
        onValueChange = {sTitle = it},
        label = { Text("Question?",
            style = MaterialTheme.typography.h6) },
        maxLines = 2
    )
    return sTitle

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
fun QuestionTypeDropDown(nSelected:Int):Int{
    var mExpanded by remember { mutableStateOf(false)}
    val types = listOf("True/False","Single-Answer","Multi-Answer")
    var selectedType by remember { mutableStateOf(nSelected)}
    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    //Icons for expanding
    val icon = if(mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(){

        OutlinedTextField(
            value = types[nSelected],
            onValueChange = {
                selectedType = types.indexOf(it)
                },
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
                        selectedType = types.indexOf(type)
                        mExpanded = false
                    }) {
                    Text(type)
                }

            }
        }
    }
    return selectedType
}

@Preview
@Composable
fun CQQPreview(){
    CreateQuestQuestionsBody(rememberNavController())
}