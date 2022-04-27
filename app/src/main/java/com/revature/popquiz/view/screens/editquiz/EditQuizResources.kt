package com.revature.popquiz.view.screens.editquiz

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.view.shared.ResourceCard
import com.revature.popquiz.viewmodel.EditQuizVM

@Composable
fun EditQuizResources(navController: NavController){

    val context = LocalContext.current

    //Grab the existing VM
    val editQuizVM =
        ViewModelProvider(context as MainActivity)
            .get(EditQuizVM::class.java)


    QuizScaffold(
        sTitle = "Quiz Resources",
        navController = navController
    ) {
        EditQuizResourcesBody(
            navController,
            context,
            editQuizVM
        )
    }
}

@Composable
fun EditQuizResourcesBody(
    navController: NavController,
    context: Context,
    editQuizVM: EditQuizVM) {
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

                Spacer(Modifier.size(40.dp))

                //Topic functionality
                EditTopic(context, editQuizVM)

                Spacer(Modifier.size(40.dp))

                //Weblink functionality
                EditWebLink(context, editQuizVM)

                Spacer(Modifier.size(40.dp))

                //Button to next screen
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.6f),
                    onClick = {

                        //Save to Quiz and navigate
                        navController.navigate(NavScreens.EditQuizQuestionSelect.route)


                    }
                ) {
                    Text(text = "Next")
                }

            }
        }
    }
}@Composable
fun EditTopic(context: Context, editQuizVM: EditQuizVM){


    var nSelected by remember { mutableStateOf(0) }
    var topicList by remember { mutableStateOf(editQuizVM.editQuiz.tagList)}

    var sTopic by remember{ mutableStateOf(
        if (editQuizVM.editQuiz.tagList.isNotEmpty())
            editQuizVM.editQuiz.tagList[nSelected]
    else
        ""
    )}

    //Text field for a new topic
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sTopic,
        onValueChange = {sTopic = it},
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

                        //if topic isnt empty and we havnt reached max topics
                        if (sTopic != "" || editQuizVM.editQuiz.tagList.size <= 5) {

                            if (editQuizVM.editQuiz.tagList.contains(sTopic)){
                                Toast.makeText(
                                    context,
                                    "Duplicate",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }else {

                                if (editQuizVM.editQuiz.tagList.isEmpty())
                                    editQuizVM.editQuiz.tagList.add(sTopic)
                                else
                                    editQuizVM.editQuiz.tagList[nSelected] = sTopic

                                topicList = mutableListOf()
                                topicList.addAll(editQuizVM.editQuiz.tagList)
                            }
                        }
                        //If we reached max topics
                        else if (editQuizVM.editQuiz.tagList.size > 5){
                            Toast.makeText(
                                context,
                                "Only 5 topics allowed",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        //If topic is empty
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

    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth(.8f)
            .border(2.dp, Color.Gray)
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.size(10.dp))
        }
        items(topicList/*editQuizVM.editQuiz.tagList*/){
            ResourceCard(sText = it,
                bSelected = nSelected == topicList.indexOf(it),
                modifier = Modifier.clickable {
                    nSelected = editQuizVM.editQuiz.tagList.indexOf(it)
                    if(nSelected < 0 || nSelected >= editQuizVM.editQuiz.tagList.size)
                        nSelected = 0
                    sTopic = editQuizVM.editQuiz.tagList[nSelected]
                }
            )
        }
        item {
            Spacer(Modifier.size(10.dp))
        }

//        Spacer(Modifier.size(10.dp))
//
//        //Display each topic thats been added
//        for (topic in topicList) {
//
//            ResourceCard(
//                sText = topic,
//                modifier = Modifier.clickable {
//                    nSelected = editQuizVM.editQuiz.tagList.indexOf(topic)
//                    sTopic = editQuizVM.editQuiz.tagList[nSelected]
//                }
//            )
////            Text(
////                text = topic,
////                style = MaterialTheme.typography.body2,
////                modifier = Modifier.clickable {
////                    nSelected = editQuizVM.editQuiz.tagList.indexOf(topic)
////                    sTopic = editQuizVM.editQuiz.tagList[nSelected]
////                }
////            )
//        }
//        Spacer(Modifier.size(10.dp))
    }
}

@Composable
fun EditWebLink(context: Context, editQuizVM: EditQuizVM){

    var nSelected by remember { mutableStateOf(0) }
    var resourceList by remember { mutableStateOf(editQuizVM.editQuiz.resourceList)}

    var sResource by remember{ mutableStateOf(
        if (editQuizVM.editQuiz.resourceList.isNotEmpty())
            editQuizVM.editQuiz.resourceList[nSelected]
        else
            ""
    )}
//    var sResource by remember { mutableStateOf(editQuizVM.editQuiz.resourceList[nSelected]) }

    //Text Field for new Link
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(.8f),
        value = sResource,
        onValueChange = {sResource = it},
        label = { Text("Resources",
            style = MaterialTheme.typography.body1) },
        trailingIcon = {

            //Add link icon
            Icon(imageVector = Icons.Default.Add,
                contentDescription = "Add Topic Icon",
                modifier = Modifier
                    .clickable {

                        //Check if the link meets requirements

                        //If link is not empty and max links hasnt been reached
                        if (sResource != "" || editQuizVM.editQuiz.resourceList.size <= 5) {

                            if (editQuizVM.editQuiz.resourceList.contains(sResource)){
                                Toast.makeText(
                                    context,
                                    "Duplicate",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }else {

                                if (editQuizVM.editQuiz.resourceList.isEmpty())
                                    editQuizVM.editQuiz.resourceList.add(sResource)
                                else
                                    editQuizVM.editQuiz.resourceList[nSelected] = sResource

                                resourceList = mutableListOf()
                                resourceList.addAll(editQuizVM.editQuiz.resourceList)
                            }
                        }
                        //If we've reached max links
                        else if (editQuizVM.editQuiz.resourceList.size > 5){
                            Toast.makeText(
                                context,
                                "Only 5 resources allowed",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                        //if the link is empty
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

    val state = rememberLazyListState()

    LazyColumn(
        state = state,
        modifier = Modifier
            .fillMaxWidth(.8f)
            .border(2.dp, Color.Gray)
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.size(10.dp))
        }
        items(/*editQuizVM.editQuiz.*/resourceList){
            ResourceCard(
                sText = it,
                bSelected = nSelected == resourceList.indexOf(it),
                modifier = Modifier.clickable {
                    nSelected = resourceList.indexOf(it)
                    Log.d("EditQuiz","Selected index = $nSelected out of ${resourceList.size}")
                    if(nSelected < 0 || nSelected >= resourceList.size)
                        nSelected = 0

                    sResource = editQuizVM.editQuiz.resourceList[nSelected]
                })
        }
        item {
            Spacer(Modifier.size(10.dp))
        }

//        Spacer(Modifier.size(10.dp))
//
//        //Display all links to user
//        for (topic in resourceList) {
//
//            ResourceCard(
//                sText = topic,
//                modifier = Modifier.clickable {
//                    nSelected = editQuizVM.editQuiz.resourceList.indexOf(topic)
//                    sResource = editQuizVM.editQuiz.resourceList[nSelected]
//                }
//            )
////            Text(
////                text = topic,
////                style = MaterialTheme.typography.body2,
////                maxLines = 1,
////                modifier = Modifier.clickable {
////                    nSelected = editQuizVM.editQuiz.resourceList.indexOf(topic)
////                    sResource = editQuizVM.editQuiz.resourceList[nSelected]
////                }
////            )
//        }
//        Spacer(Modifier.size(10.dp))

    }

}