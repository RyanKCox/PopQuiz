package com.revature.popquiz.view.shared

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun QuizScaffold(
    sTitle: String,
    navController: NavController,
    content:@Composable () -> Unit,
) {
    //Temp scaffold before we build it
    Scaffold(
        topBar = { TopAppBar (
            title = {Text(sTitle)},
            backgroundColor = MaterialTheme.colors.secondary
        ) },
        backgroundColor = MaterialTheme.colors.background,
        content = {
            content()
        }
    )
}

/**
 * Temporary Scaffold that does not take in navController
 */
@Composable
fun TempQuizScaffold(sTitle: String, content: @Composable () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar (title = {Text(sTitle)}, backgroundColor = MaterialTheme.colors.secondary) },
        backgroundColor = MaterialTheme.colors.background,
        content = { content() }
    )
}

@Composable
fun UniversalButton(
    enabled: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}