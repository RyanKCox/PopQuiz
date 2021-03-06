package com.revature.popquiz.view.screens.createflashcard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun CreateFlashcardScreen(navController: NavController) {
    QuizScaffold(
        sTitle = "Add Flashcards",
        navController = navController
    ) {
        CreateFlashcardBody(navController)
    }
}
@Composable
fun CreateFlashcardBody(navController: NavController) {
    val context = LocalContext.current
    val createFlashcardVM = ViewModelProvider(context as MainActivity).get(CreateFlashcardVM::class.java)

    var flashcardTopic by remember { mutableStateOf("") }
    var flashcardQuestion by remember { mutableStateOf("") }
    var flashcardAnswer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize(0.95f)
                .padding(15.dp),
            shape = RoundedCornerShape(40.dp),
            elevation = 10.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.size(10.dp))
                TextField(
                    value = flashcardTopic,
                    onValueChange = { flashcardTopic = it },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent, textColor = revOrange
                    )
                )
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = flashcardQuestion,
                    onValueChange = { flashcardQuestion = it },
                    label = { Text("Front side") },
                    maxLines = 2
                )
                Spacer(modifier = Modifier.size(10.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(.8f),
                    value = flashcardAnswer,
                    onValueChange = { flashcardAnswer = it },
                    label = { Text("Backside") },
                    maxLines = 2
                )
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = {
                    if (checkFields(
                            context = context,
                            flashcardTopic = flashcardTopic,
                            flashcardQuestion = flashcardQuestion,
                            flashcardAnswer = flashcardAnswer
                    )) {
//                        createFlashcardVM.newFlashcard.add(
//                            Flashcard(
//                                context = context,
//                                flashcardTopic = flashcardTopic,
//                                flashcardQuestion = flashcardQuestion,
//                                flashcardAnswer = flashcardAnswer
//                            )
//                        )
                    }
                }) {

                }
            }
        }
    }
}
@Composable
fun CreateFlashcardButton(
    context: Context,
    fTopic: String,
    fQuestion: String,
    fAnswer: String
) {
    Button(
        onClick = {
            checkFields(
                context = context,
                flashcardTopic = fTopic,
                flashcardQuestion = fQuestion,
                flashcardAnswer = fAnswer
            )
        }
    ) {
        Text(text = "Create Flashcard")
    }
}

fun checkFields(
    context: Context,
    flashcardTopic: String,
    flashcardQuestion: String,
    flashcardAnswer: String
): Boolean {
    return if (flashcardTopic.isBlank() || flashcardQuestion.isBlank() || flashcardAnswer.isEmpty()) {
        Toast.makeText(context, "Please fill all entries", Toast.LENGTH_LONG).show()
        false
    } else {
        true
    }
}

@Preview
@Composable
fun CreateFlashcardScreenPreview() {
    CreateFlashcardScreen(rememberNavController())
}