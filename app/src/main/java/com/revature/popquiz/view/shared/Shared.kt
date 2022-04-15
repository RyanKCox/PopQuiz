package com.revature.popquiz.view.shared


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.revature.popquiz.R
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revLightOrange
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.navigation.NavScreens
import com.revature.popquiz.view.screens.quizTags
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Shared Composables
 *
 * This file is for composables that are used in multiple screens
 */

/**
 * QuizScaffold
 *
 * Scaffold to be used with all screens
 */
@Composable
fun QuizScaffold(sTitle: String, navController: NavController, content: @Composable () -> Unit) {

    //Temp scaffold before we build it
    Scaffold(
        topBar =
        {
            TopAppBar(
                title = { Text(sTitle) },
                backgroundColor = MaterialTheme.colors.secondary
            )
        },
        content =
        {

            content()
        }
    )
}

/**
 * Temporary Scaffold that does not take in navController
 */
@Composable
fun TempQuizScaffold(color:Color= revBlue, sTitle: String, navController: NavController,
                     scope: CoroutineScope, scaffoldState: ScaffoldState, content: @Composable () -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            outDrawer(scope =scope , scaffoldState =scaffoldState , title =sTitle )
        },
        backgroundColor = color,
        drawerContent = { inDrawer(navController = navController , scope = scope , scaffoldState =scaffoldState )},
        scaffoldState = scaffoldState,
        content = { content() }
    )
}

@Composable
fun UniversalButton(
    enabled: Boolean,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(25.dp)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 20.sp
        )
    }
}

@Composable
fun QuizCardForLazyColumn(
    quizTitleText: String,
    shortQuizDescriptionText: String
) {
    Card(
        modifier =
        Modifier
            .clickable { }
            .height(200.dp)
            .fillMaxWidth()
            .absolutePadding(bottom = 10.dp)
            .padding(horizontal = 5.dp),
//            .border(
//                BorderStroke(
//                    3.dp,
//                    brush = Brush.horizontalGradient(
//                        colors = listOf(
//                            PurpleVariant,
//                            BluishGreen
//                        )
//                    )
//                ),
//                shape = RoundedCornerShape(25.dp)
//            ),

        shape = RoundedCornerShape(25.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.absolutePadding(top = 10.dp)
        )
        {
            Text(
                text = quizTitleText,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = shortQuizDescriptionText,
                textAlign = TextAlign.Center
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.absolutePadding(bottom = 10.dp)
        )
        {
            quizTags()
        }

    }
}

@Composable
fun basicCard(title: String, info: String) {
    Card(
        modifier = Modifier.padding(10.dp),
        elevation = 50.dp,
        shape = RoundedCornerShape(25.dp),
        backgroundColor = revLightOrange
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = title, fontSize = 20.sp,
                fontWeight = FontWeight.Medium, modifier = Modifier
                    .fillMaxWidth(0.95F)
                    .padding(horizontal = 5.dp)
            )
            Text(
                text = info, fontSize = 15.sp,
                fontWeight = FontWeight.Normal, modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(0.95F)
            )
        }
    }
}

@Preview
@Composable
fun ViewQuizCard() {
    QuizCardForLazyColumn("Quiz Title", "Short quiz description")
}
//Top drawer function
@Composable
fun outDrawer(scope: CoroutineScope, scaffoldState: ScaffoldState, title: String) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        }) {
            Icon(painter = painterResource(id = R.drawable.ic_bookblack), contentDescription = null)

        }
    }, title = {
        Text(title, modifier = Modifier.clickable {
            scope.launch {
                scaffoldState.drawerState.open()
            }
        })
    }, backgroundColor = revOrange)
}
//Drawer Menu including navigation
@Composable
fun inDrawer(navController: NavController, scope: CoroutineScope, scaffoldState: ScaffoldState) {

    Column(
        modifier = Modifier.fillMaxSize(0.9F),
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "Menu", fontSize = 20.sp, modifier = Modifier
            .clickable {
                scope.launch { scaffoldState.drawerState.close() }
            }
            .fillMaxWidth(0.9f))
        Spacer(modifier = Modifier.height(20.dp))
        Card(backgroundColor = revLightOrange, modifier = Modifier
            .fillMaxWidth(0.9F)
            .clickable {
                scope.launch {
                    navController.navigate(NavScreens.SearchQuizzesScreen.route)
                }
            }) {
            Row() {
                Text(text = "Search Quiz")

            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(backgroundColor = revLightOrange, modifier = Modifier
            .fillMaxWidth(0.9F)
            .clickable {
                scope.launch {
                    navController.navigate(NavScreens.SavedQuizzesScreen.route)
                }
            }) {
            Row() {
                Text(text = "Saved Quizzes")

            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(backgroundColor = revLightOrange, modifier = Modifier
            .fillMaxWidth(0.9F)
            .clickable {
                scope.launch {
                    navController.navigate(NavScreens.CreateQuizTitle.route)
                }
            }) {
            Row() {
                Text(text = "Create a quiz")

            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(backgroundColor = revLightOrange, modifier = Modifier
            .fillMaxWidth(0.9F)
            .clickable {
                scope.launch {
                    // navController.navigate(NavScreens..route)
                }
            }) {
            Row() {
                Text(text = "Pop! Quiz Settings")

            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(backgroundColor = revLightOrange, modifier = Modifier
            .fillMaxWidth(0.9F)
            .clickable {
                scope.launch {
                    // navController.navigate(NavScreens..route)
                }
            }) {
            Row() {
                Text(text = "Profile")

            }

        }
    }


}