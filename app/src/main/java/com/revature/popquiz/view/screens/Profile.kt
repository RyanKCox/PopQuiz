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
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.shared.QuizScaffold

@Composable
fun profile(navController: NavController)
{

    val lazyState = rememberLazyListState()

    val profile= RoomDataManager.profile.observeAsState(
        ProfileEntity()
    )



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
                                text = profile.value.name,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(20.dp)
                            )
                            Spacer(modifier = Modifier.height(20.dp))

                            Card(
                                shape = RoundedCornerShape(20.dp),
                                backgroundColor = revOrange,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.dp)
                                    .clickable { },
                                elevation = 10.dp,
                            )
                            {
                                Column(modifier = Modifier.padding(15.dp))
                                {
                                    Row()
                                    {
                                        Image(
                                            painter = painterResource(id = R.drawable.clipart_3418189__340),
                                            contentDescription = "",
                                            modifier = Modifier
                                                .height(150.dp)
                                                .width(150.dp)
                                                .fillMaxWidth(),
                                            contentScale = ContentScale.FillBounds
                                        )
                                        Text(
                                            profile.value.name
                                        )
                                    }
                                }
                            }
                        }
                        items(profile.value.pastQuizzes){runningQuiz ->
                            Card() {
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