package com.revature.popquiz.view.screens.flashcard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.navigation.NavController

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.QuestionInterface
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.ui.theme.PopQuizTheme
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun FlashCardScreen(

    navController: NavController
) {

    var lazyState = rememberLazyListState()
    val context = LocalContext.current
    var flashVM = ViewModelProvider(context as MainActivity).get(FlashcardViewModel::class.java)

    QuizScaffold(
        sTitle = "Flashcards",
        navController = navController,
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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

                    LazyColumn(
                        state = lazyState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(.5f)
                    ){

                        items(flashVM.currentQuiz?.questionList!!.toList()){
                            if (it.nType != QuestionInterface.QUESTION_TYPE_MULTI_ANSWER)
                                FlashCard(it)

                        }
                    }
                }
            }
        }
    )
}

@Composable
fun FlashCard(question: Question) {

    val duration: Int by remember { mutableStateOf(400) }
    val flipOnTouchEnabled: Boolean by remember { mutableStateOf(true) }
    val flipEnabled: Boolean by remember { mutableStateOf(true) }
    val autoFlipEnabled: Boolean by remember { mutableStateOf(false) }
    val selectedAnimType: FlipAnimationType by remember { mutableStateOf(
        FlipAnimationType.VERTICAL_ANTI_CLOCKWISE
    ) }
    val flipController = rememberFlipController()

    var sAnswer = ""
    question.answers.forEach {
        if (it.bCorrect)
            sAnswer = it.sAnswer
    }

    Flippable(
        frontSide = {
            FlashCardFrontSide(
                flipController = flipController,
                question = question.question) },
        backSide = {
            FlashCardBackSide(
                flipController = flipController,
                answer = sAnswer) },
        flipController = flipController,
        flipDurationMs = duration,
        flipOnTouch = flipOnTouchEnabled,
        flipEnabled = flipEnabled,
        autoFlip = autoFlipEnabled,
        autoFlipDurationMs = 2000,
        flipAnimationType = selectedAnimType
    )
}

@Preview
@Composable
fun FlashcardScreenPreview() {
    val navController = rememberNavController()
    PopQuizTheme {
        FlashCardScreen(navController = navController)
    }
}


