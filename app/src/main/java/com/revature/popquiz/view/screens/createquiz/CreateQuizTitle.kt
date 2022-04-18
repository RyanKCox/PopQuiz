package com.revature.popquiz.view.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.CreateQuizVM

@Composable
fun CreateQuizTitle(navController: NavController,createQuizVM: CreateQuizVM= hiltViewModel()){

    Log.d("Create Q Title Screen", "Create Q Title Start")

    //Create our VM
   // val createQuizVM = _createQuizVM

    //Use Scaffold created for app
    QuizScaffold(
        sTitle = "Quiz Title",
        navController = navController) {

        //Screen Content
        CreateQuizTitleBody(navController, createQuizVM)

    }
}

@Composable
fun CreateQuizTitleBody(
    navController: NavController,
    createQuizVM: CreateQuizVM
){

    val context = LocalContext.current

    //Temp variables for the Quiz creation
    var sQuizTitle by remember { mutableStateOf("")}
    var sShortDesc by remember { mutableStateOf("")}
    var sLongDesc by remember { mutableStateOf("")}

    //Column for the screen
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //Card Our input field is held on
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

                Spacer(Modifier.size(40.dp))

                //Text field for Quiz Title
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sQuizTitle,
                    onValueChange = {sQuizTitle = it},
                    label = {Text("Quiz Title")}
                )

                Spacer(Modifier.size(40.dp))

                //Text Field for the short Description
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sShortDesc,
                    onValueChange = {sShortDesc = it},
                    label = {Text("Short Description")}
                )

                Spacer(Modifier.size(40.dp))

                //Text field for the Full Description
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(100.dp),
                    value = sLongDesc,
                    onValueChange = {sLongDesc = it},
                    label = {Text("Full Description")},
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
                          createQuizVM.newQuiz.title = sQuizTitle
                          createQuizVM.newQuiz.shortDescription = sShortDesc
                          createQuizVM.newQuiz.longDescription = sLongDesc

                          //Navigate to next screen
                          navController.navigate(NavScreens.CreateQuizResources.route)
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

@Preview
@Composable
fun previewCQT(){
    //CreateQuizTitleBody()
}