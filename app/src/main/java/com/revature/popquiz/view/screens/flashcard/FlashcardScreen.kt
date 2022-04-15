package com.revature.popquiz.view.screens.flashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.ui.theme.PopQuizTheme
import com.revature.popquiz.view.shared.TempQuizScaffold

@Composable
fun FlashCardScreen(
    navController: NavController
) {
    val scaffoldState = rememberScaffoldState()

    TempQuizScaffold(
        sTitle = "Flashcards",
        navController = navController,
        content = {
            FlashCard()
        },
    )
}
@Composable
fun FlashCard() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val duration: Int by remember { mutableStateOf(400) }
            val flipOnTouchEnabled: Boolean by remember { mutableStateOf(true) }
            val flipEnabled: Boolean by remember { mutableStateOf(true) }
            val autoFlipEnabled: Boolean by remember { mutableStateOf(false) }
            val selectedAnimType: FlipAnimationType by remember { mutableStateOf(
                FlipAnimationType.VERTICAL_ANTI_CLOCKWISE
            ) }
            val flipController = rememberFlipController()

            Flippable(
                frontSide = { FlashCardFrontSide(flipController = flipController) },
                backSide = { FlashCardBackSide(flipController = flipController) },
                flipController = flipController,
                flipDurationMs = duration,
                flipOnTouch = flipOnTouchEnabled,
                flipEnabled = flipEnabled,
                autoFlip = autoFlipEnabled,
                autoFlipDurationMs = 2000,
                flipAnimationType = selectedAnimType
            )
        }
    }
}
@Preview
@Composable
fun FlashcardScreenPreview() {
    val navController = rememberNavController()
    PopQuizTheme {
        FlashCardScreen(navController = navController)
    }
}