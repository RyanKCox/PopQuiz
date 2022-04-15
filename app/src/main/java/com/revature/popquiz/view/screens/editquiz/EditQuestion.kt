package com.revature.popquiz.view.screens.editquiz

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.view.screens.createquiz.*
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.EditQuizVM

@Composable
fun EditQuestion(navController: NavController){

    val context = LocalContext.current

    //Grab the existing VM
    val editQuizVM =
        ViewModelProvider(context as MainActivity)
            .get(EditQuizVM::class.java)



    QuizScaffold(
        sTitle = "Edit Question",
        navController = navController
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            //Card our input field is on
            Card(
                modifier = Modifier
                    .fillMaxSize(.95f)
                    .padding(15.dp),
                shape = RoundedCornerShape(40.dp),
                elevation = 10.dp,
            ) {
                EditQuestionBody(navController, editQuizVM, context )
            }
        }
    }
}
@Composable
fun EditQuestionBody(
    navController: NavController,
    editQuizVM: EditQuizVM,
    context: Context
){

    var questionType by remember { mutableStateOf(
        editQuizVM.editQuiz.questionList[editQuizVM.editQuestionIndex].nType) }

    var sQuestionTitle by remember { mutableStateOf(
        editQuizVM.editQuiz.questionList[editQuizVM.editQuestionIndex].question) }

    var list = mutableListOf<Answer>()
    for (answer in editQuizVM.editQuiz.questionList[editQuizVM.editQuestionIndex].answers) {
        list.add(answer.copy())
    }
    var answerList:List<Answer> by remember { mutableStateOf( list)}
       // editQuizVM.editQuiz.questionList[editQuizVM.editQuestionIndex].answers)}

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.size(10.dp))

        questionType = questionTypeDropDown(questionType)

        Spacer(Modifier.size(10.dp))

        sQuestionTitle = getQuestionTitle(sQuestionTitle)

        Spacer(Modifier.size(10.dp))

        when(questionType){
            Question.QUESTION_TYPE_TRUE_FALSE->{
                answerList = trueFalseQuestion(answerList)
            }
            Question.QUESTION_TYPE_SINGLE_ANSWER->{

                answerList = questionAnswers(context,answerList)
            }
            Question.QUESTION_TYPE_MULTI_ANSWER->{

                answerList = questionAnswers(context,answerList)
            }
        }
        Row{

            //Cancel button
            Button(onClick = {
                navController.popBackStack()
            }) {

                Text(text = "Cancel", style = MaterialTheme.typography.body1)
            }

            Spacer(Modifier.size(10.dp))

            //Accept Button
            Button(onClick = {

                if (questionCheck(
                        context = context,
                        sQuestionTitle = sQuestionTitle,
                        questionType = questionType,
                        answerList = answerList
                    )) {

                    //Save and navigate
                    editQuizVM.editQuiz.questionList[editQuizVM.editQuestionIndex] =
                        Question(
                            nType = questionType,
                            question = sQuestionTitle,
                            answers = answerList
                        )
                    //Add to Room/API

                    //Clear Question
                    navController.popBackStack()
                }

            }) {

                Text(text = "Accept", style = MaterialTheme.typography.body1)
            }
        }


    }

}