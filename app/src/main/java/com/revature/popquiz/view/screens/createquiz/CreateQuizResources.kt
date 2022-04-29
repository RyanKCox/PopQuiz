package com.revature.popquiz.view.screens.createquiz

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.view.shared.ResourceCard
import com.revature.popquiz.view.shared.TextEnums
import com.revature.popquiz.view.shared.TextLengthPrompt
import com.revature.popquiz.viewmodel.CreateQuizVM

@Composable
fun CreateQuizResources(navController: NavController){

    val context = LocalContext.current

    //Grab the existing VM
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
                val state = rememberLazyListState()

                LazyColumn(
                    state = state,
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {

                        Spacer(Modifier.size(40.dp))

                        //Topic functionality
                        TopicView(context, createQuizVM)

                        Spacer(Modifier.size(40.dp))

                        //Weblink functionality
                        WebLinkView(context, createQuizVM)

                        Spacer(Modifier.size(40.dp))

                        //Button to next screen
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
                        Spacer(Modifier.size(40.dp))
                    }

                }
            }

    }

}

@Composable
fun TopicView(context: Context, createQuizVM: CreateQuizVM){


    var sTopic by rememberSaveable { mutableStateOf("") }

    //Text field for a new topic

    if(sTopic.length > TextEnums.MAX_TOPIC_LENGTH) {
        TextLengthPrompt(maxLength = TextEnums.MAX_TOPIC_LENGTH)
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sTopic,
        onValueChange = {sTopic = it},
        maxLines = 1,
        label = { Text(
            "Topics",
                style = MaterialTheme.typography.body1)
                },
        trailingIcon = {

            //Add topic icon
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add Topic Icon",
                modifier = Modifier
                    .clickable {

                        //Check if the topic meets requirements and add to quiz

                        if(sTopic.length > TextEnums.MAX_TOPIC_LENGTH){
                            Toast.makeText(
                                context,
                                "Topic too long!",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        //if topic isnt empty and we havnt reached max topics
                        else if (sTopic != "" && createQuizVM.newQuiz.tagList.size < 5) {


                            if (createQuizVM.newQuiz.tagList.contains(sTopic)){
                                Toast.makeText(
                                    context,
                                    "Duplicate",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }else {
                                createQuizVM.newQuiz.tagList.add(sTopic)
                                //topicList.add(sTopic)
                                sTopic = ""
                            }
                        }
                        //If we reached max topics
                        else if (createQuizVM.newQuiz.tagList.size >= 5){
                            Toast.makeText(
                                context,
                                "Only 5 topics allowed",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        //If topic is empty
                        else{
                            Toast.makeText(
                                context,
                                "Nothing in Topic",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            )
        }
    )

    Spacer(Modifier.size(20.dp))

    val state = rememberLazyListState()
    val config = LocalConfiguration.current

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth(.8f)
            .border(2.dp, Color.Gray)
            .height((config.screenHeightDp / 6).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Spacer(Modifier.size(10.dp))
        }
        items(createQuizVM.newQuiz.tagList){
            ResourceCard(sText = it)
        }
        item {
            Spacer(Modifier.size(10.dp))
        }

//        Spacer(Modifier.size(10.dp))
//
//        //Display each topic thats been added
//        for (topic in createQuizVM.newQuiz.tagList) {
//
//            ResourceCard(sText = topic)
////            Text(
////                text = topic,
////                style = MaterialTheme.typography.body2
////            )
//        }
//        Spacer(Modifier.size(10.dp))

    }

}

@Composable
fun WebLinkView(context: Context, createQuizVM: CreateQuizVM){

    var sResource by rememberSaveable { mutableStateOf("") }

    //Text Field for new Link
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sResource,
        onValueChange = {sResource = it},
        label = { Text("Resources",
                style = MaterialTheme.typography.body1) },
        maxLines = 2,
        trailingIcon = {

            //Add link icon
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add Topic Icon",
                modifier = Modifier
                    .clickable {

                        //Check if the link meets requirements

                        //If link is not empty and max links hasnt been reached
                        if (sResource != "" && createQuizVM.newQuiz.resourceList.size < 5) {

                            if (createQuizVM.newQuiz.resourceList.contains(sResource)){
                                Toast.makeText(
                                    context,
                                    "Duplicate",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }else {

                                //Add to resource list and clear input
                                createQuizVM.newQuiz.resourceList.add(sResource)
                                sResource = ""
                            }
                        }
                        //If we've reached max links
                        else if (createQuizVM.newQuiz.resourceList.size >= 5){
                            Toast.makeText(
                                context,
                                "Only 5 resources allowed",
                                Toast.LENGTH_LONG
                            ).show()

                        }
                        //if the link is empty
                        else{
                            Toast.makeText(
                                context,
                                "Nothing in Resource",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            )
        }
    )

    Spacer(Modifier.size(20.dp))

    val state = rememberLazyListState()
    val config = LocalConfiguration.current

    LazyColumn(
        state= state,
        modifier = Modifier
            .fillMaxWidth(.8f)
            .border(2.dp, Color.Gray)
            .height((config.screenHeightDp / 6).dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            Spacer(Modifier.size(10.dp))
        }
        items(createQuizVM.newQuiz.resourceList){
            ResourceCard(sText = it)
        }
        item {
            Spacer(Modifier.size(10.dp))
        }

//        //Display all links to user
//        for (topic in createQuizVM.newQuiz.resourceList) {
//
//            ResourceCard(sText = topic)
////            Text(
////                text = topic/*.substringAfterLast('/')*/,
////                style = MaterialTheme.typography.body2,
////                maxLines = 1
////            )
//        }
//        Spacer(Modifier.size(10.dp))

    }

}

@Preview
@Composable
fun CQRPreview(){
    //CreateQuizResourcesBody()
}