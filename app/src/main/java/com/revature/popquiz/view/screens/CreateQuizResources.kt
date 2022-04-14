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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.dataobjects.QuizResource
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.CreateQuizVM

@Composable
fun CreateQuizResources(navController: NavController){

    val context = LocalContext.current
    val createQuizVM =
        ViewModelProvider(context as MainActivity)
            .get(CreateQuizVM::class.java)

    QuizScaffold(
        sTitle = "Quiz Resources",
        navController = navController
    ) {
        CreateQuizResourcesBody(navController,createQuizVM)
    }

}

@Composable
fun CreateQuizResourcesBody(navController: NavController, createQuizVM: CreateQuizVM){

    val context = LocalContext.current


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

                Spacer(Modifier.size(40.dp))

                TopicView(context, createQuizVM)

                Spacer(Modifier.size(40.dp))

                WebLinkView(context, createQuizVM)

                Spacer(Modifier.size(40.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(.6f),
                    onClick = {

                        //Save to Quiz and navigate
                        navController.navigate(NavScreens.CreateQuizQuestions.route)



                    }
                ) {
                    Text(text = "Next")
                }

            }
        }
    }

}

@Composable
fun TopicView(context: Context, createQuizVM: CreateQuizVM){


    var sTopic by remember { mutableStateOf("") }
    //val topicList:ArrayList<String> = arrayListOf()

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sTopic,
        onValueChange = {sTopic = it},
        label = { Text(
            "Topics",
                style = MaterialTheme.typography.body1)
                },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add Topic Icon",
                modifier = Modifier
                    .clickable {
                        if (sTopic != "" || createQuizVM.newQuiz.tagList.size <= 5) {
                            createQuizVM.newQuiz.tagList.add(sTopic)
                            //topicList.add(sTopic)
                            sTopic = ""
                        }
                        else if (createQuizVM.newQuiz.tagList.size > 5){
                            Toast.makeText(
                                context,
                                "Only 5 topics allowed",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        else{
                            Toast.makeText(
                                context,
                                "Nothing in Topic",
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
            .height(110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Dummy Data
//        topicList.add("Kotlin")
//        topicList.add("Java")
//        topicList.add("Context")
//        topicList.add("Testing")
//        topicList.add("Services")
//

        Spacer(Modifier.size(10.dp))

        for (topic in createQuizVM.newQuiz.tagList) {

            Text(
                text = topic,
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(Modifier.size(10.dp))

    }

}

@Composable
fun WebLinkView(context: Context, createQuizVM: CreateQuizVM){

    var sResource by remember { mutableStateOf("") }
    //val resourceList:ArrayList<String> = arrayListOf()

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sResource,
        onValueChange = {sResource = it},
        label = { Text("Resources",
                style = MaterialTheme.typography.body1) },
        trailingIcon = {
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add Topic Icon",
                modifier = Modifier
                    .clickable {
                        if (sResource != "" || createQuizVM.newQuiz.resourceList.size <= 5) {
                            createQuizVM.newQuiz.resourceList.add(QuizResource(sResource))
                            //resourceList.add(sResource)
                            sResource = ""
                        }
                        else if (createQuizVM.newQuiz.resourceList.size > 5){
                            Toast.makeText(
                                context,
                                "Only 5 resources allowed",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        else{
                            Toast.makeText(
                                context,
                                "Nothing in Resource",
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
            .height(110.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Dummy Data
//        resourceList.add("https://developer.android.com/kotlin/style-guide")
//        resourceList.add("https://developer.android.com/kotlin/style-guide")
//        resourceList.add("https://developer.android.com/kotlin/style-guide")
//        resourceList.add("https://developer.android.com/kotlin/style-guide")
//        resourceList.add("https://developer.android.com/kotlin/style-guide")


        Spacer(Modifier.size(10.dp))

        for (topic in createQuizVM.newQuiz.resourceList) {

            Text(
                text = topic.getResourceShortName(),
                style = MaterialTheme.typography.body2
            )
        }
        Spacer(Modifier.size(10.dp))

    }

}

@Preview
@Composable
fun CQRPreview(){
    //CreateQuizResourcesBody()
}