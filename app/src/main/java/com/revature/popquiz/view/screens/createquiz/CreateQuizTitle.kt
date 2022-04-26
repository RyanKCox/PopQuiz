package com.revature.popquiz.view.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
//import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.view.shared.TextEnums
import com.revature.popquiz.view.shared.TextLengthPrompt
import com.revature.popquiz.viewmodel.CreateQuizVM

@Composable
fun CreateQuizTitle(
    navController: NavController
)
{

    Log.d("Create Q Title Screen", "Create Q Title Start")

    //Grab our viewmodel
    val context = LocalContext.current
    val createQuizVM =
        ViewModelProvider(context as MainActivity)
            .get(CreateQuizVM::class.java)

//    createQuizVM.createNewQuiz()

    //Use Scaffold created for app
    QuizScaffold(
        sTitle = "Quiz Title",
        navController = navController)
    {

        //Screen Content
        CreateQuizTitleBody(
            navController,
            createQuizVM
        )

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
    var bTitleTooLong by remember{ mutableStateOf(false)}
    var bShortTooLong by remember{ mutableStateOf(false)}
    var bLongTooLong by remember{ mutableStateOf(false)}

    //Column for the screen
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
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
        )
        {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

                Spacer(Modifier.size(40.dp))

                //Text field for Quiz Title
                if(sQuizTitle.length > TextEnums.MAX_TITLE_LENGTH) {
                    bTitleTooLong = true
                    TextLengthPrompt(maxLength = TextEnums.MAX_TITLE_LENGTH)
                } else {bTitleTooLong = false}

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sQuizTitle,
                    onValueChange = {sQuizTitle = it},
                    label = {Text("Quiz Title")},
                    maxLines = 2
                )

                Spacer(Modifier.size(40.dp))

                //Text Field for the short Description
                if(sShortDesc.length > TextEnums.MAX_TITLE_LENGTH)
                {
                    bShortTooLong = true
                    TextLengthPrompt(maxLength = TextEnums.MAX_TITLE_LENGTH)
                }
                else {bShortTooLong = false}
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = sShortDesc,
                    onValueChange = {sShortDesc = it},
                    label = {Text("Short Description")},
                    maxLines = 2
                )

                Spacer(Modifier.size(40.dp))

                //Text field for the Full Description
                if(sLongDesc.length > TextEnums.MAX_DESCRIPTION_LENGTH)
                {
                    bLongTooLong = true
                    TextLengthPrompt(maxLength = TextEnums.MAX_DESCRIPTION_LENGTH)
                }
                else { bLongTooLong = false}
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
                        if(bTitleTooLong || bShortTooLong || bLongTooLong){
                            Toast.makeText(
                                context,
                                "Too many characters!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        //if all 3 fields have text
                        else if (sQuizTitle != "" &&
                            sShortDesc != "" &&
                            sLongDesc != ""){ //Set the values in the new quiz
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