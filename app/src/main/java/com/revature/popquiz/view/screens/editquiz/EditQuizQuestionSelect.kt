package com.revature.popquiz.view.screens.editquiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.ui.theme.PopQuizTheme
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.EditQuizVM

@Composable
fun EditQuizQuestionSelect(navController: NavController){

    val context = LocalContext.current

    //Grab the existing VM
    val editQuizVM =
        ViewModelProvider(context as MainActivity)
            .get(EditQuizVM::class.java)



    QuizScaffold(
        sTitle = "Select Question",
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

                EditQuizQuestionBody(
                    navController = navController,
                    editQuizVM = editQuizVM)

            }
        }
    }
}
@Composable
fun EditQuizQuestionBody(
    navController: NavController,
    editQuizVM: EditQuizVM){

    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.padding(5.dp)) {
            Text(
                text = "Select Question to Edit",
                style = MaterialTheme.typography.h5
            )
        }
        Divider(thickness = 5.dp)


        val lazyState = rememberLazyListState()

        LazyColumn(
            state = lazyState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var nNumber = 1
            items(editQuizVM.editQuiz.questionList) { question ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth(.9f)
                        .padding(10.dp)
                        .clickable {

                            //Navigate and save question
                            editQuizVM.editQuestionIndex =
                                editQuizVM.editQuiz.questionList.indexOf(question)

                            navController.navigate(NavScreens.EditQuestion.route)

                        },
                    elevation = 10.dp,
                    shape = RoundedCornerShape(10.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        //Question Type and delete button
                        Row {

                            Text(
                                text = question.getQuestionType(),
                                style = MaterialTheme.typography.h6
                            )
                        }

                        //Question number and Question text
                        Row(
                            modifier = Modifier
                                .padding(2.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {


                            Text(
                                //"$nNumber. ",
                                "${editQuizVM.editQuiz.questionList.indexOf(question)+1}",
                                style = MaterialTheme.typography.body1
                            )


                            Text(
                                text = question.question,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                    nNumber++

                }
            }

        }
        Divider(thickness = 5.dp)

        Spacer(Modifier.size(10.dp))

        //Add question Button
        Button(
            modifier = Modifier
                .fillMaxWidth(.6f),
            onClick = {

                //Create an empty question
                editQuizVM.editQuiz.questionList.add(
                    Question(
                        nType = 0,
                        question = "",
                        answers = listOf()
                    ))

                //Create a question and pass to edit question screen
                editQuizVM.editQuestionIndex =
                    editQuizVM.editQuiz.questionList.lastIndex

                navController.navigate(NavScreens.EditQuestion.route)

            }
        ){
            Text(text = "Add Question")

        }

        Spacer(Modifier.size(10.dp))

        //Finish Editing button
        Button(
            modifier = Modifier
                .fillMaxWidth(.6f),
            onClick = {

                //Save to Quiz and navigate
                navController.popBackStack(NavScreens.EditQuizTitle.route,true)
            }
        ) {
            Text(text = "Done")
        }
    }

}

@Preview
@Composable
fun EditPreview(){
    PopQuizTheme {
        val nav = rememberNavController()
        val editVm = EditQuizVM(Quiz())
        EditQuizQuestionBody(navController = nav, editQuizVM = editVm)
    }
}