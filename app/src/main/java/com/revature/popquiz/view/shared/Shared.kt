package com.revature.popquiz.view.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
fun QuizCard()
{
    Card(
        modifier =
        Modifier
            .height(535.dp)
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
        Row(verticalAlignment = Alignment.Top)
        {

        }
    }
}

@Preview
@Composable
fun ViewQuizCard()
{

}