package com.revature.popquiz.view.screens.editquiz

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.QuizEditor
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.EditQuizVM

@Composable
fun EditQuizTitle(navController: NavController){

    val context = LocalContext.current


    //Create our VM - dummy

    var tempQuiz = Quiz()
    tempQuiz.title = "Kotlin Overview"
    tempQuiz.shortDescription = "This quiz covers kotlin basics"
    tempQuiz.longDescription = "This quiz covers an overview of Kotlin. We will go over things like:" +
            " Function Creation, Declaring variables, strings, lambdas, and much more."
    tempQuiz.tagList.add("Kotlin")
    tempQuiz.tagList.add("Beginner")
    tempQuiz.resourceList.add("https://developer.android.com/kotlin")
    tempQuiz.resourceList.add("https://kotlinlang.org/docs/android-overview.html")
    tempQuiz.questionList.add(Question(
        nType = 0,
        question = "var and val are how u create new variables",
        answers = mutableListOf(
            Answer("True",true),
            Answer("False",false)
        )
    ))
    tempQuiz.questionList.add(Question(
        nType = 1,
        question = "How do you get the size of a string?",
        answers = mutableListOf(
            Answer("myString as Int",false),
            Answer("myString.toInt()",false),
            Answer("myString.length",true),
            Answer("myString.size()",false),
        )
    ))
   // QuizEditor.focusQuiz = tempQuiz

    val editQuizVM = ViewModelProvider(context as MainActivity).get(EditQuizVM::class.java)


    //Use Scaffold created for app
    QuizScaffold(
        sTitle = "Edit Title",
        navController = navController) {

        //Screen Content
        EditTitleBody(navController = navController, editQuizVM = editQuizVM)

    }
}
@Composable
fun EditTitleBody(
    navController: NavController,
    editQuizVM: EditQuizVM
){
    val context = LocalContext.current

    //Temp variables for the Quiz creation
    var sQuizTitle by remember { mutableStateOf(editQuizVM.editQuiz.title) }
    var sShortDesc by remember { mutableStateOf(editQuizVM.editQuiz.shortDescription) }
    var sLongDesc by remember { mutableStateOf(editQuizVM.editQuiz.longDescription) }

    //Column for the screen
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
        ){

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.size(40.dp))

                //Text field for Quiz Title
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sQuizTitle,
                    onValueChange = {sQuizTitle = it},
                    label = { Text("Quiz Title") }
                )

                Spacer(Modifier.size(40.dp))

                //Text Field for the short Description
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sShortDesc,
                    onValueChange = {sShortDesc = it},
                    label = { Text("Short Description") }
                )

                Spacer(Modifier.size(40.dp))

                //Text field for the Full Description
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(100.dp),
                    value = sLongDesc,
                    onValueChange = {sLongDesc = it},
                    label = { Text("Full Description") },
                    maxLines = 3
                )

                Spacer(Modifier.size(40.dp))

                //Next Screen Button
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.6f),
                    onClick = {

                        //Save variables and navigate

                        //if all 3 fields have text
                        if (sQuizTitle != "" ||
                            sShortDesc != "" ||
                            sLongDesc != ""){

                            //Set the values in the new quiz
                            editQuizVM.editQuiz.title = sQuizTitle
                            editQuizVM.editQuiz.shortDescription = sShortDesc
                            editQuizVM.editQuiz.longDescription = sLongDesc

                            //Navigate to next screen
                            navController.navigate(NavScreens.EditQuizResource.route)
                        } else {

                            //If everything isnt filled out
                            Toast.makeText(
                                context,
                                "Please fill out all fields",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    },
                ) {

                    Text(text = "Next")

                }

            }


        }

    }

}