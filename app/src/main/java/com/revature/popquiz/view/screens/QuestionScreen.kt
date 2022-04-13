package com.revature.popquiz.view.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.revature.popquiz.R
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
private fun QuestionScreen(navController: NavController) {

    QuizScaffold(sTitle = R.string.quiz_name.toString(), navController = navController) {

        Column(modifier = Modifier.fillMaxSize()) {

        }
    }
}