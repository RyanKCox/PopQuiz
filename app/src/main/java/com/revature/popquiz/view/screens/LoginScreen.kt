package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.TextFieldDefaults.UnfocusedIndicatorLineOpacity
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.R
import com.revature.popquiz.model.datastore.LoginDataStore
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.navigation.NavScreens
import androidx.compose.material.TextFieldDefaults.UnfocusedIndicatorLineOpacity
import androidx.compose.ui.text.input.VisualTransformation
import com.revature.popquiz.ui.theme.revDarkGrey

@Composable
fun LoginScreen(navController: NavController)
{
    val context=LocalContext.current
    val dataStore= LoginDataStore(context)
    val userEmail = dataStore.getEmail.collectAsState(initial = "")
    val userPass = dataStore.getPass.collectAsState(initial = "")
    var sEmail by rememberSaveable { mutableStateOf("") }
    var sPass by rememberSaveable { mutableStateOf("") }
    
    //Dummy Setup
    Log.d("Login Screen", "Login Screen Start")
    //Shared Scaffold - May not use in this screen
    Scaffold(backgroundColor = revOrange,
        topBar =
        { TopAppBar(backgroundColor = revDarkGrey)
        {
            Text(
                text = "Login",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(horizontal =5.dp),
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Card(
                modifier = Modifier
                    .fillMaxSize(),
//                    .absolutePadding(
//                        top = 15.dp,
//                        left = 5.dp,
//                        right = 5.dp
//                    ),
//                shape = AbsoluteRoundedCornerShape(
//                    topLeft = 20.dp,
//                    topRight = 20.dp
//                ),
                elevation = 10.dp
            )
            {
                //Screen Content
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    Image(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(shape = RoundedCornerShape(10.dp)),
                        painter = painterResource(id = R.drawable.rev_logo_2),
                        contentDescription = null
                    )
                    Spacer(Modifier.height(40.dp))

                    TextField(
                        shape = CircleShape,
                        value = sEmail,
                        onValueChange = { sEmail = it },
                        label = { Text("Email: ") },
                        modifier = Modifier
                            .height(60.dp)
                            .width(330.dp)
                    )

                    Spacer(Modifier.height(25.dp))

                    TextField(
                        shape = CircleShape,
                        value = sPass,
                        onValueChange = { sPass = it },
                        label =
                        {
                            Text(
                                text = "Password: "
                            )
                        },
                        modifier = Modifier
                            .height(60.dp)
                            .width(330.dp)
                    )

                    Spacer(Modifier.height(35.dp))

                    //Button to navigate
                    Button(
                        onClick =
                        {
                            //dummy navigation
                            if (sPass==userPass.value && sEmail==userEmail.value) {
                                navController.navigate(NavScreens.SavedQuizzesScreen.route)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = revOrange)
                    )
                    {
                        Text(
                            text = "Login",
                            textAlign = TextAlign.Center,
                            color= Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "New user?: Register",
                        fontSize = 15.sp,
                        fontStyle = FontStyle.Italic,
                        modifier = Modifier
                            .clickable
                            {navController.navigate(NavScreens.RegistrationScreen.route)},
                        color = revBlue)
                }
            }
        }
    }
}
@Composable
@Preview
fun loginPreview()
{
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}