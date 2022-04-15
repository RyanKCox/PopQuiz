package com.revature.popquiz.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.revature.popquiz.ui.theme.Teal200
import com.revature.popquiz.ui.theme.revBlue


@Composable
    fun quizComplete() {
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
                                    text = "Quiz Complete",
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
                                    elevation = 10.dp
                                ) {
                                    Column(
                                       // modifier = Modifier.padding(15.dp)
                                    ) {
                                        Text(
                                            ("Congratulations! \n Your Score is \n %")
                                        )

                                    }
                                }

                                    reviewAnswersButton()
                                    shareButton()
                                    shareButton()
                                    exitButton()

                            }

                        }

                    }

                }
            }
        }
    }
}

                            @Composable
                            fun reviewAnswersButton(/*navController: NavController*/) {
                                Button(modifier = Modifier
                                    .padding(10.dp)
                                    .height(100.dp)
                                    .width(150.dp)
                                    .wrapContentHeight(),
                                    shape = RoundedCornerShape(25),
                                    onClick = {

                                        //Send toy to trade request screen and navigate
                                        // navController.navigate(NavScreens.AcceptTradeScreen.route)
                                    })
                                {
                                    Text(
                                        text = "Review Answers",
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }


                            @Composable
                            fun shareButton(/*navController: NavController*/) {
                                Button(modifier = Modifier
                                    .padding(10.dp)
                                    .height(100.dp)
                                    .width(150.dp)
                                    .wrapContentHeight(),
                                    shape = RoundedCornerShape(25),
                                    onClick = {

                                        //Send toy to trade request screen and navigate
                                        // navController.navigate(NavScreens.AcceptTradeScreen.route)
                                    })
                                {
                                    Text(
                                        text = "Share With Friends",
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }


                            @Composable
                            fun exitButton(/*navController: NavController*/) {
                                Button(modifier = Modifier
                                    .padding(10.dp)
                                    .height(100.dp)
                                    .width(150.dp)
                                    .wrapContentHeight(),
                                    shape = RoundedCornerShape(25),
                                    onClick = {

                                        //Send toy to trade request screen and navigate
                                        // navController.navigate(NavScreens.AcceptTradeScreen.route)
                                    })
                                {
                                    Text(
                                        text = "Exit",
                                        fontSize = 15.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
@Composable
fun quizFinishedScreen(/*navController: NavController*/) {
    val context = LocalContext.current
//    val userToysViewModel =
//        ViewModelProvider(context as MainActivity).get(UserToysViewModel::class.java)
//
//
//    Scaffold(
//        bottomBar = { BottomBar(navController) },
//        topBar = { Header(text = userToysViewModel.toy?.sName ?: "My Toy") })
//    {
//        Surface()
//        {
//            Image(
//                painter = painterResource(id = R.drawable.minimal_blue_toy_background),
//                contentDescription = "he",
//                modifier = Modifier
//                    .height(1000.dp)
//                    .width(700.dp)
//                    .fillMaxWidth(),
//                contentScale = ContentScale.FillBounds
//            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
//                    .background(
//                        Brush.horizontalGradient(
//                            colors = listOf(
//                                Teal200
//                            )
//                        )
//                    ),
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
            )
            {

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
                            quizComplete()
                            reviewAnswersButton()
                            shareButton()
                            shareButton()
                            exitButton()

                        }

                    }
                }
            }
        }

@Preview
@Composable
fun prevA(){
    quizComplete()
}
