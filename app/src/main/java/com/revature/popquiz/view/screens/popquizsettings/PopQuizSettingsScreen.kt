package com.revature.popquiz.view.screens.popquizsettings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun PopQuizSettingsScreen(navController: NavController) {
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

@Preview
@Composable
fun PreviewSettings() {
    PopQuizSettingsScreen(navController = rememberNavController())
}