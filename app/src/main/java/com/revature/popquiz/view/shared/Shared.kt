package com.revature.popquiz.view.shared

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
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
        topBar = { TopAppBar (
            title = {Text(sTitle)},
            backgroundColor = MaterialTheme.colors.secondary
                ) },
        content = {
            content()
        }
    )

}
