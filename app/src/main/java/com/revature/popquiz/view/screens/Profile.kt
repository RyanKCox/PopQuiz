package com.revature.popquiz.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Room
import com.revature.popquiz.R
import com.revature.popquiz.model.datastore.LoginDataStore
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.model.room.profileroom.ProfileEntity
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun profile(navController: NavController)
{

    val lazyState = rememberLazyListState()

    val profile= RoomDataManager.profile.observeAsState(
        ProfileEntity()

    )

    val checkedState=remember{ mutableStateOf(false)}

    QuizScaffold(
        sTitle = "",
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                )
                {
                    LazyColumn(
                        state = lazyState,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = Color.Transparent
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    )
                    {
                        item{


                            Text(
                                text = profile.value.name.uppercase(),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(20.dp)
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            Card(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(0.95F),
                                elevation = 50.dp,
                                shape = RoundedCornerShape(25.dp),
                                backgroundColor = revOrange
                            ) {
                                Column(modifier = Modifier.padding(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Top) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 20.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = "Subscribe To Pop Quizzes",
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier
                                                .fillMaxWidth(0.40F)
                                                .padding(horizontal = 0.dp)
                                        )
                                        Switch(
                                            checked = checkedState.value,
                                            onCheckedChange = {
                                                checkedState.value = it
                                                //Create if else for on /off
                                            },
                                            colors = SwitchDefaults.colors(
                                                revOrange
                                            )
                                        )


                                    }

                                }
                            }
                        }
                        items(profile.value.pastQuizzes){runningQuiz ->
                            Card(        modifier = Modifier.padding(10.dp),
                                elevation = 50.dp,
                                shape = RoundedCornerShape(25.dp),
                                backgroundColor = revLightOrange)
                            {
                                Row(modifier = Modifier.padding(5.dp)){
                                    Text(text = runningQuiz )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun prevProfile()
{
    //profile()
}