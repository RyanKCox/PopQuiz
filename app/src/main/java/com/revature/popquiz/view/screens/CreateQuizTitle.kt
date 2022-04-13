package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun CreateQuizTitle(navController: NavController){

    Log.d("Create Q Title Screen", "Create Q Title Start")

    QuizScaffold(
        sTitle = "Quiz Title",
        navController = navController) {

        //Screen Content
        CreateQuizTitleBody()

    }
}

@Composable
fun CreateQuizTitleBody(){

    var sQuizTitle by remember { mutableStateOf("")}
    var sShortDesc by remember { mutableStateOf("")}
    var sLongDesc by remember { mutableStateOf("")}

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
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sQuizTitle,
                    onValueChange = {sQuizTitle = it},
                    label = {Text("Quiz Title")}
                )

                Spacer(Modifier.size(40.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sShortDesc,
                    onValueChange = {sShortDesc = it},
                    label = {Text("Short Description")}
                )

                Spacer(Modifier.size(40.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(200.dp),
                    value = sLongDesc,
                    onValueChange = {sLongDesc = it},
                    label = {Text("Full Description")},
                    maxLines = 4
                )
                
                Spacer(Modifier.size(40.dp))
                
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.6f),
                    onClick = {

                          //Save variables and navigate

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
    CreateQuizTitleBody()
}