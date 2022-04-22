package com.revature.popquiz.view.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.MainActivity
import com.revature.popquiz.model.QuizEditor
import com.revature.popquiz.model.dataobjects.Answer
import com.revature.popquiz.model.dataobjects.Question
import com.revature.popquiz.model.dataobjects.Quiz
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.screens.question.QuestionViewModel
import com.revature.popquiz.view.shared.QuizScaffold

import com.revature.popquiz.view.shared.basicCard
import com.revature.popquiz.viewmodel.CreateQuizVM
import com.revature.popquiz.viewmodel.QuizOverviewVM


@Composable
fun quizOverView(navController: NavController)
{
    val context= LocalContext.current
    val quizOverviewVM =
        ViewModelProvider(context as MainActivity)
            .get(QuizOverviewVM::class.java)
    val questionVM =
        ViewModelProvider(context as MainActivity)
            .get(QuestionViewModel::class.java)
    val quiz=quizOverviewVM.quiz


    val checkedState=remember{ mutableStateOf(false)}

    QuizScaffold(sTitle = "Quiz Overview", navController = navController)
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        )
        {
            LazyRow(modifier=Modifier.fillMaxWidth())
            {
                item ()
                {
                    Spacer(modifier = Modifier.fillParentMaxWidth(0.05F))
                    Column(modifier=Modifier.fillParentMaxWidth(0.9F)) {


                        Card(
                            shape = RoundedCornerShape(25.dp),
                            elevation = 50.dp,
                            modifier = Modifier
                                .fillMaxHeight(0.9F)
                                .fillMaxWidth()
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize(fraction = 0.9F),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            )
                            {
                                item {
                                    Spacer(modifier = Modifier.height(20.dp))
                                    Text(
                                        text = "${quiz?.title} Quiz",
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Medium,
                                        modifier = Modifier.padding(20.dp)
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
//description
                                    basicCard(title = "Description:", info = quiz?.longDescription?:"")

                                    Spacer(modifier = Modifier.height(20.dp))
//Topics
                                    Card(
                                        modifier = Modifier.padding(10.dp),
                                        elevation = 50.dp,
                                        shape = RoundedCornerShape(25.dp),
                                        backgroundColor = revLightOrange
                                    ) {
                                        Column(modifier = Modifier.padding(10.dp)) {
                                            Text(
                                                text = "Topics: ", fontSize = 20.sp,
                                                fontWeight = FontWeight.Medium, modifier = Modifier
                                                    .fillMaxWidth(0.95F)
                                                    .padding(horizontal = 5.dp)
                                            )

                                            Row() {
                                                quiz?.tagList?.forEach { tag ->
                                                    Text(
                                                        text = "-$tag",
                                                        fontSize = 15.sp,
                                                        fontWeight = FontWeight.Normal,
                                                        modifier = Modifier
                                                            .padding(horizontal = 7.dp)
                                                    )
                                                }

                                            }

                                        }
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))


//Subscribe
                                    Card(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .fillMaxWidth(0.95F),
                                        elevation = 50.dp,
                                        shape = RoundedCornerShape(25.dp),
                                        backgroundColor = revLightOrange
                                    ) {
                                        Column(modifier = Modifier.padding()) {
                                            Row(
                                                modifier = Modifier.padding(horizontal = 20.dp),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(
                                                    text = "Subscribe",
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Medium,
                                                    modifier = Modifier
                                                        .fillMaxWidth(0.95F)
                                                        .padding(horizontal = 0.dp)
                                                )
                                                Switch(
                                                    checked = checkedState.value,
                                                    onCheckedChange = { checkedState.value = it },
                                                    colors = SwitchDefaults.colors(
                                                        revOrange
                                                    )
                                                )


                                            }

                                        }
                                    }
                                    Spacer(modifier = Modifier.height(20.dp))
//Sample Quiz


                                        basicCard(
                                            title = "Sample Question: ",
                                            info = "Sample Question"
                                        )




                                    Spacer(modifier = Modifier.height(20.dp))
//Buttons
                                    Column(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalArrangement = Arrangement.Bottom
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(0.9F),
                                            horizontalArrangement = Arrangement.SpaceEvenly
                                        ) {
                                            quizViewButton(text = "Edit", modifier = Modifier
                                                .fillParentMaxWidth(0.22F)
                                                .height(50.dp), onclick = {

                                                    QuizEditor.focusQuiz=quiz?:Quiz()
                                                    navController.navigate(NavScreens.EditQuizTitle.route)

                                            })
                                            quizViewButton(text = "Start", modifier = Modifier
                                                .fillParentMaxWidth(0.22F)
                                                .height(50.dp), onclick = {

                                                    questionVM.quiz=quiz?:Quiz()
                                                    navController.navigate(NavScreens.QuestionScreen.route)
                                            })
                                            quizViewButton(text = "Cards", modifier = Modifier
                                                .fillParentMaxWidth(0.22F)
                                                .height(50.dp), onclick = {

                                                    //Send Correct Quiz
                                                    navController.navigate(NavScreens.FlashcardScreen.route)

                                            })


                                        }
                                        Spacer(modifier = Modifier.height(35.dp))
                                    }

                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.width(25.dp))

                }
                //This is the scrollable page
                item() {
                    Column(modifier = Modifier.fillParentMaxWidth(0.9F)) {
                        Card(
                            shape = RoundedCornerShape(25.dp),
                            elevation = 50.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(fraction = 0.9F),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Top
                            )
                            {
                                Text(
                                    text = "Resources",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.padding(20.dp)
                                )
                                Spacer(modifier = Modifier.height(50.dp))

                            }

                        }

                    }
                    Spacer(modifier = Modifier.fillParentMaxWidth(0.05F))

                }

            }
        }

    }


}





//------------------------------------Composables and Preview-----------------------------//



@Composable
fun quizViewButton(text:String,modifier: Modifier,onclick:
()->Unit)
{
    Button(
        onClick = { onclick()}, colors = ButtonDefaults.buttonColors(
            backgroundColor = revOrange
        ), modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
@Preview
fun quizPreview()
{
    val navController= rememberNavController()
    quizOverView(navController)
}