package com.revature.popquiz.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.popquiz.R
import com.revature.popquiz.ui.theme.revBlue

@Composable
fun profile(/*navController: NavController*/) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
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
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                item() {
                    Spacer(modifier = Modifier.fillParentMaxWidth(0.05F))
                    Column(modifier = Modifier.fillParentMaxWidth(0.9F)) {


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
                                    text = "Profile",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(20.dp)
                                )
                                Spacer(modifier = Modifier.height(20.dp))
                                Card(
                                    backgroundColor = revBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                        .clickable { },
                                    elevation = 10.dp,
                                ) {
                                    Column(
                                        modifier = Modifier.padding(15.dp)
                                    ) {
                                        Row {
                                            Image(
                                                painter = painterResource(id = R.drawable.clipart_3418189__340),
                                                contentDescription = "",
                                                modifier = Modifier
                                                    .height(150.dp)
                                                    .width(150.dp)
                                                    .fillMaxWidth(),
                                                contentScale = ContentScale.FillBounds
                                            )
                                            Text(
                                                ("Name \n" +
                                                        " Your Overall Score \n" +
                                                        " Pop! Quiz Score \n" +
                                                        " Quizzes Completed ")
                                            )
                                        }
                                    }
                                }

                                Card(
                                    backgroundColor = revBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                        .clickable { },
                                    elevation = 10.dp
                                ) {
                                    Column(
                                        modifier = Modifier.padding(15.dp)
                                    ) {
                                        Text(
                                            ("About me / subject interests \n" +
                                                    " \n" +
                                                    " \n" +
                                                    "  ")
                                        )
                                    }
                                }
                                Card(
                                    backgroundColor = revBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                        .clickable { },
                                    elevation = 10.dp
                                ) {
                                    Column(
                                        modifier = Modifier.padding(15.dp)
                                    ) {
                                        Text(
                                            ("C Quiz \n" +
                                                    " % \n" +
                                                    " \n" +
                                                    "  ")
                                        )
                                    }
                                }
                                Card(
                                    backgroundColor = revBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                        .clickable { },
                                    elevation = 10.dp
                                ) {
                                    Column(
                                        modifier = Modifier.padding(15.dp)
                                    ) {
                                        Text(
                                            ("C++ Quiz \n" +
                                                    " % \n" +
                                                    " \n" +
                                                    "  ")
                                        )
                                    }
                                }
                                Card(
                                    backgroundColor = revBlue,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                        .clickable { },
                                    elevation = 10.dp
                                ) {
                                    Column(
                                        modifier = Modifier.padding(15.dp)
                                    ) {
                                        Text(
                                            ("Kotlin \n" +
                                                    " % \n" +
                                                    " \n" +
                                                    "  ")
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun prevProfile(){
    profile()
}
