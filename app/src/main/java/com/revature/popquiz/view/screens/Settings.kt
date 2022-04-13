package com.revature.popquiz.view.screens

import androidx.compose.foundation.layout.*
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
import com.revature.popquiz.ui.theme.revBlue


@Composable
fun Settings() {
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
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Spacer(Modifier.size(30.dp))
            Card(shape = RoundedCornerShape(25.dp), elevation = 50.dp, modifier = Modifier.fillMaxSize(fraction = 0.9F)) {
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
    Settings()
}