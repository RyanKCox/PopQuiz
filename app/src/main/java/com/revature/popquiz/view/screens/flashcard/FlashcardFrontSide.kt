package com.revature.popquiz.view.screens.flashcard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun FlashCardFrontSide(flipController: FlippableController, question:String) {
    Box(modifier = Modifier) {
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(25.dp),
            color = MaterialTheme.colors.secondaryVariant,
            elevation = 16.dp
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                    val (txtShuffleWord, txtWord, IblTapToFlip) = createRefs()
                    
                    Text(
                        text = question,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .constrainAs(txtWord) {
                                linkTo(
                                    start = parent.start,
                                    end = parent.end
                                )
                                linkTo(
                                    top = parent.top,
                                    bottom = parent.bottom
                                )
                            }

                    )
//                    Text(
//                        text = "Tap to flip",
//                        style = MaterialTheme.typography.subtitle2,
//                        color = Color.DarkGray,
//                        maxLines = 1,
//                        modifier = Modifier
//                            .constrainAs(IblTapToFlip) {
//                                linkTo(
//                                    start = parent.start,
//                                    end = parent.end
//                                )
//                                bottom.linkTo(parent.bottom)
//                            }
//                            .padding(bottom = 8.dp)
//                    )
//
//                    Button(
//                        onClick = { flipController.flip() },
//                        modifier = Modifier
//                            .constrainAs(txtShuffleWord) {
//                                top.linkTo(parent.top)
//                                end.linkTo(parent.end)
//                            }
//                            .padding(end = 8.dp)
//                    ) {
//                        Text(
//                            text = "Show definition",
//                            style = MaterialTheme.typography.subtitle2,
//                            color = Color.Black,
//                            maxLines = 1
//                        )
//                    }
                }

            }
        }
    }
}

