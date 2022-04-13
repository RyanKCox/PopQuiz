package com.revature.popquiz.view.shared

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/**
 * Shared Composables
 *
 * This file is for composables that are used in multiple screens
 */

/**
 * QuizScaffold
 *
 * Scaffold to be used with all screens
 */
@Composable
fun QuizScaffold(sTitle:String, navController: NavController, content:@Composable () -> Unit){

    //Temp scaffold before we build it
    Scaffold(
        topBar =
        {
            TopAppBar(
                title = {Text(sTitle)},
                backgroundColor = MaterialTheme.colors.secondary
            )
        },
        content =
        {
            content()
        }
    )

}

@Composable
fun QuizCardForLazyColumn()
{
    Card(
        modifier =
        Modifier
            .height(400.dp)
            .fillMaxWidth()
            .absolutePadding(bottom = 10.dp)
            .padding(horizontal = 5.dp),
//            .border(
//                BorderStroke(
//                    3.dp,
//                    brush = Brush.horizontalGradient(
//                        colors = listOf(
//                            PurpleVariant,
//                            BluishGreen
//                        )
//                    )
//                ),
//                shape = RoundedCornerShape(25.dp)
//            ),

        shape = RoundedCornerShape(25.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.absolutePadding(top = 10.dp)
        )
        {
            quizTitle(quizTitleText = "Quiz Title")
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            shortQuizDescription(shortQuizDescriptionText = "Short quiz description")
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.absolutePadding(bottom = 10.dp)
        )
        {
            quizTags()
        }

    }
}


@Composable
fun quizTitle(
    quizTitleText: String
)
{
    Text(
        text = quizTitleText,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun shortQuizDescription(
    shortQuizDescriptionText: String
)
{
    Text(
        text = shortQuizDescriptionText,
        textAlign = TextAlign.Center
    )
}

@Composable
fun quizTags()
{
    Text(
        text = "Tags: ",
        textAlign = TextAlign.Center
    )
    // Insert tags here

    val tags: Set<String>
    tags = setOf("Language", " Topic", " Quiz type")

    Text(
        text = tags.toString(),
        textAlign = TextAlign.Center
    )
}


@Preview
@Composable
fun ViewQuizCard()
{
    QuizCardForLazyColumn()
}