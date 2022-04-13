package com.revature.popquiz.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.revature.popquiz.R

@Composable
fun profile(/*navController: NavController*/) {
    val context = LocalContext.current
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(25.dp)
    ) {
        Surface()
        {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clickable { },
                    elevation = 10.dp
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


@Preview
@Composable
fun prevProfile(){
    profile()
}
