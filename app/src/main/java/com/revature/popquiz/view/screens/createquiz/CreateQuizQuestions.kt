package com.revature.popquiz.view.screens.createquiz

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
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
import com.revature.popquiz.model.QuestionInterface
import com.revature.popquiz.model.QuizEditor
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.CreateQuizVM
import com.revature.popquiz.viewmodels.QuizManager

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
    var questionType by remember { mutableStateOf(0)}
    var answerList:MutableList<Answer> = mutableListOf()//by remember { mutableStateOf(listOf())}

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.size(10.dp))

                questionType = questionTypeDropDown(questionType,answerList)

                Spacer(Modifier.size(10.dp))

                sQuestionTitle = getQuestionTitle(sQuestionTitle)

                Spacer(Modifier.size(10.dp))

                when(questionType){
                    QuestionInterface.QUESTION_TYPE_TRUE_FALSE->{
                        answerList = trueFalseQuestion(answerList)
                    }
                    QuestionInterface.QUESTION_TYPE_SINGLE_ANSWER->{

                        answerList = questionAnswers(context,answerList)
                    }
                    QuestionInterface.QUESTION_TYPE_MULTI_ANSWER->{

                        answerList = questionAnswers(context,answerList)
                    }
                }

                Row {

                    //Button for Done
                    Button(
                        onClick = {

                            if (questionCheck(
                                    context = context,
                                    sQuestionTitle = sQuestionTitle,
                                    questionType = questionType,
                                    answerList = answerList
                                )) {

                                //Save and navigate
                                createQuizVM.newQuiz.questionList.add(
                                    Question(
                                        nType = questionType,
                                        question = sQuestionTitle,
                                        answers = answerList
                                    )
                                )
                                //Add to Room/API
                                createQuizVM.saveQuiz()
                                QuizManager.loadQuizzes()


                                navController.popBackStack(NavScreens.CreateQuizTitle.route, true)

                            }
                        }
                    ) {

                        Text(text = "Finish", style = MaterialTheme.typography.body1)
                    }

                    Spacer(Modifier.size(10.dp))

                    //Button for New Question
                    Button(
                        onClick = {

                            if (questionCheck(
                                    context = context,
                                    sQuestionTitle = sQuestionTitle,
                                    questionType = questionType,
                                    answerList = answerList
                                )) {

                                    //Save and navigate
                                    createQuizVM.newQuiz.questionList.add(
                                        Question(
                                            nType = questionType,
                                            question = sQuestionTitle,
                                            answers = answerList
                                        )
                                    )
                                    //Add to Room/API

                                    //Clear Question
                                    navController.navigate(NavScreens.CreateQuizQuestions.route)
                                }
                            }
                    )
                            {

                        Text(text = "Add", style = MaterialTheme.typography.body1)
                    }
                }
            }
        }
    }
}
fun questionCheck(
    context: Context,
    sQuestionTitle:String,
    questionType:Int,
    answerList:List<Answer>
):Boolean{

    var bChecked = false

    if (sQuestionTitle == "") {
        Toast.makeText(
            context,
            "Question Field must be filled out",
            Toast.LENGTH_LONG
        ).show()

    } else if (questionType == QuestionInterface.QUESTION_TYPE_SINGLE_ANSWER) {

        var nCount = 0
        answerList.forEach { answer ->
            if (answer.bCorrect)
                nCount++

        }

        if (nCount != 1) {
            Toast.makeText(
                context,
                "Must have 1 correct answer",
                Toast.LENGTH_LONG
            ).show()
        } else {
            bChecked = true
        }

    } else if (questionType == QuestionInterface.QUESTION_TYPE_MULTI_ANSWER) {
        var bHasAnswer = false
        answerList.forEach { answer ->
            if (answer.bCorrect) {
                bHasAnswer = true
            }

        }
        if (!bHasAnswer) {
            Toast.makeText(
                context,
                "At least one answer has to be correct",
                Toast.LENGTH_LONG
            ).show()
        } else {
            bChecked = true

        }
    } else if (questionType == QuestionInterface.QUESTION_TYPE_TRUE_FALSE) {

        bChecked = true

    }
    return bChecked

}

@Composable
fun trueFalseQuestion(answers:MutableList<Answer>):MutableList<Answer> {

    var answerList:MutableList<Answer> = answers //by remember {mutableStateOf( answer)}


    var trueCheck by remember { mutableStateOf(if(answerList.isNotEmpty()) answerList[0].bCorrect else true) }
    var falseCheck by remember { mutableStateOf(if(answerList.isNotEmpty()) answerList[1].bCorrect else false) }

    if(answerList.isEmpty()) {
        answerList.add(Answer("True", trueCheck))
        answerList.add(Answer("False", falseCheck))
    } /*else {
        trueCheck = answerList[0].bCorrect
        falseCheck = answerList[1].bCorrect
    }*/


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
fun questionAnswers(context:Context, answers:MutableList<Answer>):MutableList<Answer>{

    var sAnswer by remember { mutableStateOf("")}
    var answerList:MutableList<Answer> = answers
//    var answerList by remember {mutableStateOf( answers.toTypedArray())}


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
                contentDescription = "Add Answer Icon",
                modifier = Modifier
                    .clickable {
                        if (sAnswer.isNotEmpty() && answerList.size <= 3) {
                            answerList.add(Answer(sAnswer,false))
                            sAnswer = ""
                        }
                        else if (answerList.size > 3){
                            Toast.makeText(
                                context,
                                "Only 4 answers allowed",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        else{
                            Toast.makeText(
                                context,
                                "Nothing in Answer Field",
                                Toast.LENGTH_LONG
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

            var checkAnswer by remember { mutableStateOf(answer.bCorrect)}

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 5.dp)) {

                Checkbox(
                    checked = checkAnswer,
                    onCheckedChange = {
                        checkAnswer = !checkAnswer
                        answer.bCorrect = checkAnswer
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
    return answerList
}

@Composable
fun getQuestionTitle(sTitle:String):String{

    var sTitle by remember { mutableStateOf(sTitle) }

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
fun questionTypeDropDown(nSelected:Int, answers:MutableList<Answer>):Int{

    var mExpanded by remember { mutableStateOf(false)}
    val types = listOf("True/False","Single-Answer","Multi-Answer")
    var selectedType by remember { mutableStateOf(nSelected)}
    var textFieldSize by remember { mutableStateOf(Size.Zero)}

    //Icons for expanding
    val icon = if(mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column{

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
                        if(selectedType == 0){
                            answers.clear()
                        }
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