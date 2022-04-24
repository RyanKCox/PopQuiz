package com.revature.popquiz.view.screens.editquiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.revature.popquiz.model.room.RoomDataManager
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

    var showDialog by remember { mutableStateOf(false)}
    var removeIndex  = 0

    //Delete question if returning from a cancel add question
    if(editQuizVM.nDeleteQuestionIndex != null){
        editQuizVM.editQuiz.questionList.removeAt(editQuizVM.nDeleteQuestionIndex!!)
        editQuizVM.nDeleteQuestionIndex = null
    }


    //Alert dialog for deleting question
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false

            },
            title = {
                Text("Delete Question?")
            },
            text = {
                Text("Do you want to delete this question?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        editQuizVM.editQuiz.questionList.removeAt(removeIndex)
                        showDialog = false
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        )
    }


    
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
                                text = question.nType.toString(),
                                style = MaterialTheme.typography.h6
                            )

                            Row(
                                horizontalArrangement = Arrangement.End,
                                modifier = Modifier.fillMaxWidth()
                            ) {

                                //Delete icon
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Delete Question",
                                    modifier = Modifier
                                        .clickable {
                                            showDialog = true
                                            removeIndex = editQuizVM.editQuiz.questionList.indexOf(question)

                                        }
                                )
                            }
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
                                "${editQuizVM.editQuiz.questionList.indexOf(question)+1}. ",
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
                        answers = mutableListOf()
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
                editQuizVM.updateQuizToRoom()

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
        val editVm = EditQuizVM()
        editVm.editQuiz = Quiz()
        EditQuizQuestionBody(navController = nav, editQuizVM = editVm)
    }
}