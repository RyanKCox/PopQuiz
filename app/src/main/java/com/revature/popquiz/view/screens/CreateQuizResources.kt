package com.revature.popquiz.view.screens

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun CreateQuizResources(navController: NavController){

    QuizScaffold(
        sTitle = "Quiz Resources",
        navController = navController
    ) {
        CreateQuizResourcesBody()
    }

}

@Composable
fun CreateQuizResourcesBody(){

    val context = LocalContext.current

    var sTopic by remember { mutableStateOf("") }
    var topicList:ArrayList<String> = arrayListOf()


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
                    value = sTopic,
                    onValueChange = {sTopic = it},
                    label = { Text("Topics") },
                    trailingIcon = {
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "Add Topic Icon",
                            modifier = Modifier
                                .clickable {
                                    if (sTopic != "" || topicList.size <= 5) {
                                        topicList.add(sTopic)
                                        sTopic = ""
                                    }
                                    else if (topicList.size > 5){
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

                Spacer(Modifier.size(40.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(.8f)
                        .height(200.dp)
                        .padding(5.dp)
                        .border(2.dp, Color.Gray)
                ) {
                    topicList.add("Kotlin")
                    topicList.add("Java")
                    topicList.add("Context")
                    topicList.add("Testing")
                    topicList.add("Services")

                    for (topic in topicList) {

                            Text(text = topic)
                    }

                }

            }
        }
    }

}

@Preview
@Composable
fun CQRPreview(){
    CreateQuizResourcesBody()
}