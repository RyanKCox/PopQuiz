package com.revature.popquiz.view.screens.question

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.QuestionInterface
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.shared.QuizScaffold
import com.revature.popquiz.viewmodel.QuizFinishedVM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat

@Composable
fun QuestionScreen(navController: NavController) {
    val context= LocalContext.current
    val questionVM =
        ViewModelProvider(context as MainActivity)
            .get(QuestionViewModel::class.java)


    val scaffoldState = rememberScaffoldState()
    val quiz = questionVM.quiz.copy()
    val runningQuiz=RunningQuiz()

    runningQuiz.title=quiz.title
    runningQuiz.questions=quiz.questionList
    runningQuiz.maxScore=runningQuiz.questions.size.toFloat()


    QuizScaffold(
        sTitle = "TAKE QUIZ",
        navController = navController,
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = "${quiz.title.uppercase()} QUIZ",
                            fontSize = 40.sp,
                            modifier = Modifier.padding(20.dp)
                        )

                        QuestionCard(runningQuiz, navController,questionVM)
                    }
                }

            }
        }
    )
}

@Composable
fun ProgressBar() {
    Box(modifier = Modifier
        .fillMaxHeight(0.1f)
        .fillMaxWidth()
        .padding(10.dp)) {

    }
    val progressBar: ProgressBar
}

@Composable
fun QuestionCard(quiz: RunningQuiz,navController: NavController,viewModel: QuestionViewModel) {

    val scrollState= rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        quiz.questions.forEach { question->
            Card(modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(10.dp),
                shape = RoundedCornerShape(25.dp), backgroundColor = revLightOrange
            ) {
                Column (modifier = Modifier.padding(10.dp)){
                    Text(text = question.question,fontSize = 18.sp,
                        fontWeight = FontWeight.Medium)
                    question.answers.forEach {

//                        answerCardMultiAnswer(
//                            answer = it,
//                            question = question,
//                            quiz = quiz
//                        )
                        if(question.nType != QuestionInterface.QUESTION_TYPE_MULTI_ANSWER) {
                            answerCardSingleAnswer(
                                answer = it,
                                question = question,
                                quiz = quiz,
                                viewModel = viewModel
                            )
                        } else{
                            answerCardMultiAnswer(
                                answer = it,
                                question = question,
                                quiz = quiz,
                                viewModel = viewModel
                            )

                        }

                    }
                }

            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        SubmitButton(quiz, navController)
        Spacer(modifier = Modifier.height(20.dp))

    }
}

@Composable
fun AnswerButton(answer: String) {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(10.dp),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
    ) {
        Text(text = answer)
    }
//    UniversalButton(
//        enabled = true,
//        text = answer,
//        onClick = { /*TODO*/ },
//        modifier = Modifier.fillMaxWidth(0.8f).padding(10.dp)
//    )
}

@Composable
fun SubmitButton(quiz: RunningQuiz, navController: NavController) {
    val context= LocalContext.current
    val scope= rememberCoroutineScope()
    val profile=RoomDataManager.profile.observeAsState()

    val questionVM =
        ViewModelProvider(context as MainActivity)
            .get(QuestionViewModel::class.java)

    //Check if all questions were answered
    var bCompleted by remember{ mutableStateOf(true)}
    bCompleted = true

    quiz.questions.forEach {  question ->
        if (questionVM.questionAnswer.containsKey(question)){

            if (questionVM.questionAnswer.isEmpty())
                bCompleted = false

        } else{
            bCompleted = false
        }
    }

        Button(
            enabled = bCompleted,
            onClick = {

                //go to finished screen

                //Only navigate if all questions completed
                if(bCompleted){


                    quiz.oneAnswerQuestion.clear()
                    questionVM.questionAnswer.forEach { question->

                        var list = mutableListOf<Answer>()
                        question.value.forEach { answer ->
                            list.add(answer)
                        }
                        quiz.oneAnswerQuestion.put(question.key,list)

                    }

                    quiz.score=0F
                    val score = calculateScore(quiz)
                    quiz.finalScore=score*100
                    questionVM.runningQuiz=quiz
                    var formatedScore = NumberFormat.getInstance().format(quiz.finalScore)
                    RoomDataManager.profile.value?.pastQuizzes?.add("${quiz.title}: $formatedScore")


                    scope.launch(Dispatchers.IO) {
                        Log.d("jcstn","Profile ${profile.value}")
                        RoomDataManager.profileRepository.insertProfile(profile = profile.value!!)

                    }
                    val finishVM = ViewModelProvider(context).get(QuizFinishedVM::class.java)
                    finishVM.quiz = questionVM.runningQuiz
                    navController.navigate(NavScreens.QuizFinishScreen.route)
                }

                      },
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(10.dp),
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = revLightOrange)
        ) {
            Text(text = "Submit")
        }
   // }
}

//@Preview()
//@Composable
//fun QuestionScreenPreview() {
//    val navController = rememberNavController()
//    PopQuizTheme {
//        QuestionScreen(navController = navController)
//    }
//}

@Composable
fun answerCardSingleAnswer(quiz: RunningQuiz,question: Question, answer: Answer, viewModel:QuestionViewModel)
{

    var color  = Color.White

    if (viewModel.questionAnswer.containsKey(question)){
        if (viewModel.questionAnswer[question]!!.contains(answer)){
            color = Color.Green
        }
    }

//    if(quiz.oneAnswerQuestion.containsKey(question)){
//        if(quiz.oneAnswerQuestion[question]!!.contains(answer) /*== answer*/){
//            color = Color.Green
//        }
//    }

    Card(modifier = Modifier
        .fillMaxWidth(0.9F)
        .padding(5.dp)
        .clickable {

            if (viewModel.questionAnswer.containsKey(question)) {
                if (viewModel.questionAnswer[question]!!.contains(answer)/*==answer*/) {
                    viewModel.questionAnswer.remove(question)
                } else {
                    viewModel.questionAnswer
                        .get(question)!!
                        .clear()
                    viewModel.questionAnswer[question]!!.add(answer)

//                    viewModel.questionAnswer.remove(question)
//                    quiz.oneAnswerQuestion.put(question,answer)
                }
            } else {
                viewModel.questionAnswer.put(question, mutableStateListOf(answer))
            }

//            if (quiz.oneAnswerQuestion.containsKey(question)) {
//                if (quiz.oneAnswerQuestion[question]!!.contains(answer)/*==answer*/) {
//                    quiz.oneAnswerQuestion.remove(question)
//                    viewModel.questionAnswer.remove(question)
//                } else {
//                    quiz.oneAnswerQuestion
//                        .get(question)!!
//                        .clear()
//                    quiz.oneAnswerQuestion[question]!!.add(answer)
//
//                    viewModel.questionAnswer.remove(question)
////                    quiz.oneAnswerQuestion.put(question,answer)
//                }
//            } else {
//                quiz.oneAnswerQuestion.put(question, mutableStateListOf(answer))
//            }

        }   ,
        backgroundColor = color) {
        Text(text = answer.sAnswer, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())

    }
}
@Composable
fun answerCardMultiAnswer(quiz: RunningQuiz,question: Question, answer: Answer,viewModel: QuestionViewModel)
{

    var color  = Color.White


    if (viewModel.questionAnswer.containsKey(question)){
        if (viewModel.questionAnswer[question]!!.contains(answer)){
            color = Color.Green
        }
    }

//    if(quiz.oneAnswerQuestion.containsKey(question)){
//        if(quiz.oneAnswerQuestion[question]!!.contains(answer)){
//            color = Color.Green
//        }
//    }

    Card(modifier = Modifier
        .fillMaxWidth(0.9F)
        .padding(5.dp)
        .clickable {
            if (viewModel.questionAnswer.containsKey(question)) {
                if (viewModel.questionAnswer[question]!!.contains(answer)) {
                    viewModel.questionAnswer[question]!!.remove(answer)
                    if (viewModel.questionAnswer[question]!!.isEmpty()) {
                        viewModel.questionAnswer.remove(question)
                    }
                } else {
                    viewModel.questionAnswer[question]?.add(answer)
//                    quiz.oneAnswerQuestion.put(question,answer)
                }
            } else {
                var answerList = mutableStateListOf<Answer>(answer)
                viewModel.questionAnswer.put(question, answerList)
            }

//            if (quiz.oneAnswerQuestion.containsKey(question))
//            {
//                if (quiz.oneAnswerQuestion[question]!!.contains(answer))
//                {
//                    quiz.oneAnswerQuestion[question]!!.remove(answer)
//                    if (quiz.oneAnswerQuestion[question]!!.isEmpty()){
//                        quiz.oneAnswerQuestion.remove(question)
//                    }
//                }
//                else{
//                    quiz.oneAnswerQuestion[question]?.add(answer)
////                    quiz.oneAnswerQuestion.put(question,answer)
//                }
//            }else
//            {
//                var answerList = mutableStateListOf<Answer>(answer)
//                quiz.oneAnswerQuestion.put(question,answerList)
//            }

        }   ,
        backgroundColor = color) {
        Text(text = answer.sAnswer, modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth())

    }
}
fun calculateScore(quiz: RunningQuiz):Float
{
    var finalScore:Float=0F

    quiz.questions.forEach { question->

        var bCorrect = true
        question.answers.forEach { answer->

            // if a wrong answer is selected
            if(!answer.bCorrect&&quiz.oneAnswerQuestion[question]!!.contains(answer))
            {
                bCorrect = false
            //            quiz.score++
            }
            //if a right answer is not selected
            else if (answer.bCorrect && !quiz.oneAnswerQuestion[question]!!.contains(answer)){
                bCorrect = false
            }
        }
        if (bCorrect)
            quiz.score++
    }
        finalScore=(quiz.score/quiz.maxScore!!)
        return finalScore
}