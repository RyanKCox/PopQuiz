package com.revature.popquiz.view.screens

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.ui.theme.revOrange


@Composable
fun quizOverView() {
    val quiz = Quiz()
    quiz.title="Arrays"
    quiz.longDescription="This quiz reviews the basics of arrays in Kotlin." +
            " This includes properties of arrays and their usage"
    quiz.tagList.add(0,"Index")
    quiz.tagList.add(1,"Arrays")
    val scaffoldState = rememberScaffoldState()
    val context= LocalContext.current


    Scaffold(
        backgroundColor = revBlue,
        topBar = {/*header*/ },
        scaffoldState = scaffoldState
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        )
        {
            LazyRow(modifier=Modifier.fillMaxWidth()) {
               item (){
                   Spacer(modifier = Modifier.fillParentMaxWidth(0.05F))
                   Column(modifier=Modifier.fillParentMaxWidth(0.9F)) {


                       Card(
                           shape = RoundedCornerShape(25.dp),
                           elevation = 50.dp,
                           modifier = Modifier
                               .fillMaxHeight(0.9F)
                               .fillMaxWidth()
                       ) {
                           Column(
                               modifier = Modifier.fillMaxSize(fraction = 0.9F),
                               horizontalAlignment = Alignment.CenterHorizontally,
                               verticalArrangement = Arrangement.Top
                           )
                           {
                               Text(
                                   text = "${quiz.title} Quiz",
                                   fontSize = 30.sp,
                                   fontWeight = FontWeight.Medium,
                                   modifier = Modifier.padding(20.dp)
                               )
                               Spacer(modifier = Modifier.height(20.dp))
                               Card(
                                   modifier = Modifier.padding(10.dp),
                                   elevation = 50.dp,
                                   shape = RoundedCornerShape(25.dp),
                                   backgroundColor = revLightOrange
                               ) {
                                   Column(modifier = Modifier.padding(10.dp)) {
                                       Text(
                                           text = " Description:", fontSize = 20.sp,
                                           fontWeight = FontWeight.Medium, modifier = Modifier
                                               .fillMaxWidth(0.95F)
                                               .padding(horizontal = 5.dp)
                                       )
                                       Text(
                                           text = quiz.longDescription, fontSize = 15.sp,
                                           fontWeight = FontWeight.Normal, modifier = Modifier
                                               .padding(10.dp)
                                               .fillMaxWidth(0.95F)
                                       )
                                   }
                               }
                               Spacer(modifier = Modifier.height(20.dp))
                               Card(
                                   modifier = Modifier.padding(10.dp),
                                   elevation = 50.dp,
                                   shape = RoundedCornerShape(25.dp),
                                   backgroundColor = revLightOrange
                               ) {
                                   Column(modifier = Modifier.padding(10.dp)) {
                                       Text(
                                           text = "Topics: ", fontSize = 20.sp,
                                           fontWeight = FontWeight.Medium, modifier = Modifier
                                               .fillMaxWidth(0.95F)
                                               .padding(horizontal = 5.dp)
                                       )

                                       Row() {
                                           quiz.tagList.forEach { tag ->
                                               Text(
                                                   text = "-$tag",
                                                   fontSize = 15.sp,
                                                   fontWeight = FontWeight.Normal,
                                                   modifier = Modifier
                                                       .padding(horizontal = 7.dp)
                                               )
                                           }

                                       }

                                   }
                               }
                               Spacer(modifier = Modifier.height(20.dp))


                               Row(
                                   modifier = Modifier.fillMaxWidth(0.9F),
                                   horizontalArrangement = Arrangement.SpaceEvenly
                               ) {
                                   Button(
                                       onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                           backgroundColor = revOrange
                                       ), modifier = Modifier.fillParentMaxWidth(0.2F)
                                           .height(50.dp)
                                   ) {
                                       Text(text = "Edit")
                                   }
                                   Button(
                                       onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                           backgroundColor = revOrange
                                       ), modifier = Modifier.fillParentMaxWidth(0.2F)
                                           .height(50.dp)
                                   ) {
                                       Text(text = "Start")
                                   }
                                   Button(
                                       onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                           backgroundColor = revOrange
                                       ), modifier = Modifier.fillParentMaxWidth(0.2F)
                                           .height(50.dp)
                                   ) {
                                       Text(text = "Cards")
                                   }
                               }
                           }
                       }

                   }
                   Spacer(modifier = Modifier.width(25.dp))

               }
                //This is the scrollable page
                item() {
                    Column(modifier = Modifier.fillParentMaxWidth(0.9F)) {
                        Card(
                            shape = RoundedCornerShape(25.dp),
                            elevation = 50.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(fraction = 0.9F),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            )
                            {
                                Text(
                                    text = "Resources",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(20.dp)
                                )
                                Spacer(modifier = Modifier.height(50.dp))

                            }

                        }

                    }
                    Spacer(modifier = Modifier.fillParentMaxWidth(0.05F))

                }

            }
        }

    }
}

@Composable
@Preview
fun quizPreview()
{
    quizOverView()
}