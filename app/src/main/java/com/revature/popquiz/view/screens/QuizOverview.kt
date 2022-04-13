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
import com.revature.popquiz.ui.theme.revBlue

@Composable
fun quizOverView() {
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
                                   text = "Quiz Name", fontSize = 30.sp,
                                   fontWeight = FontWeight.Medium, modifier = Modifier.padding(20.dp)
                               )
                               Spacer(modifier = Modifier.height(50.dp))

                           }
                       }

                   }
                   Spacer(modifier = Modifier.width(25.dp))

               }
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