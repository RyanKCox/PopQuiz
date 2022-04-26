package com.revature.popquiz.view.screens.popquizsettings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.model.api.services.popquiz.setAlarm
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun PopQuizSettingsScreen(navController: NavController) {
    val context = LocalContext.current
    QuizScaffold(sTitle = "Pop!Quiz Settings", navController = navController) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Set Alarm for 5 seconds",
                modifier = Modifier.padding(10.dp),
                fontSize = 16.sp
            )
            Button(onClick = {
                setAlarm(context)
            }) {
                Text(text = "Set Alarm")
            }

        }
    }
}

@Preview
@Composable
fun PreviewSettings() {
    PopQuizSettingsScreen(navController = rememberNavController())
}