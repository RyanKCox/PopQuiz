package com.revature.popquiz.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.view.shared.QuizScaffold


@Composable
fun Settings(navController: NavController)
{
    val scaffoldState = rememberScaffoldState()
    val context= LocalContext.current

    QuizScaffold(
        sTitle = "Settings",
        navController = navController
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Card(
                modifier = Modifier
                    .fillMaxSize(),
//                    .absolutePadding(
//                        top = 15.dp,
//                        left = 5.dp,
//                        right = 5.dp
//                    ),
//                shape = AbsoluteRoundedCornerShape(
//                    topLeft = 20.dp,
//                    topRight = 20.dp
//                ),
                elevation = 10.dp
            )
            {
                Column(
                    modifier = Modifier.fillMaxSize(fraction = 0.9F),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    Text(text = "POP QUIZ", fontSize = 30.sp,
                        fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(50.dp))

                    Text(text = "Quiz Intervals", fontSize = 20.sp,
                        fontWeight = FontWeight.Medium, modifier = Modifier.fillMaxWidth(0.9F)
                        , textAlign = TextAlign.Left)

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = "Quiz Length", fontSize = 20.sp,
                        fontWeight = FontWeight.Medium, modifier = Modifier.fillMaxWidth(0.9F)
                        , textAlign = TextAlign.Left)

                    Spacer(modifier = Modifier.height(30.dp))

                    Text(text = "Alert Type", fontSize = 20.sp,
                        fontWeight = FontWeight.Medium, modifier = Modifier.fillMaxWidth(0.9F)
                        , textAlign = TextAlign.Left)


                }
            }
        }

    }
}

@Composable
@Preview
fun setPreview()
{
    //Settings()
}