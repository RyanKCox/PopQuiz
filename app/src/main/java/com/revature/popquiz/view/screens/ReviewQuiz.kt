package com.revature.popquiz.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.model.datastore.LoginDataStore
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.screens.question.QuestionViewModel
import com.revature.popquiz.view.screens.question.RunningQuiz
import com.revature.popquiz.view.shared.QuizScaffold
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun reviewQuiz(navController: NavController) {
    val context= LocalContext.current
    val questionVM =
        ViewModelProvider(context as MainActivity)
            .get(QuestionViewModel::class.java)
    val quiz = questionVM.runningQuiz
    val dataStore= LoginDataStore(context)
    val userEmail = dataStore.getEmail.collectAsState(initial = "")
    val scope = rememberCoroutineScope()


    QuizScaffold(
        sTitle = "Review Quiz",
        navController = navController
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
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
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${questionVM.quiz.title.uppercase()} QUIZ",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(10.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){
                            items(quiz.questions){question->
                                Card(modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .padding(10.dp),
                                    shape = RoundedCornerShape(25.dp), backgroundColor = revLightOrange) {
                                    Column (modifier = Modifier.padding(10.dp)){
                                        Text(text = question.question,fontSize = 18.sp,
                                        fontWeight = FontWeight.Medium)
                                        question.answers.forEach {
                                            answers(answer = it, quiz = quiz, question = question)

                                        }
                                    }

                                }
                            }
                            item{
                                Button(onClick = {
                                    val profile = RoomDataManager.profileRepository.fetchProfileWithEmail(userEmail.value?:"")
                                    profile?.pastQuizzes?.add(quiz)

                                    scope.launch(Dispatchers.IO) {RoomDataManager.profileRepository.insertProfile(profile = profile)}

                                    navController.popBackStack(NavScreens.SavedQuizzesScreen.route,inclusive = false)

                                                 }, colors = ButtonDefaults.buttonColors(
                                    revOrange)) {
                                    Text(text = "Done")
                                }
                            }
                        }



                }


            }
        }
    }

}

//-----------------------------------------Composables and preview---------------------//

@Composable
fun answers(answer: Answer,quiz: RunningQuiz,question: Question)
{
    Card(modifier = Modifier
        .fillMaxWidth(0.9F)
        .padding(5.dp),
        backgroundColor =
        when{
            answer.bCorrect -> Color.Green
            !answer.bCorrect&&answer==quiz.oneAnswerQuestion[question] -> Color.Red
            else -> Color.White
        }

    )

    {
        Text(text = answer.sAnswer, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())

    }
}

@Composable
@Preview
fun reviewPreview()
{
    val navController= rememberNavController()
    reviewQuiz(navController)
}