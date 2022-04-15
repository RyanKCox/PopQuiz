package com.revature.popquiz.view.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun LoginScreen(navController: NavController) {
    val context=LocalContext.current
    val dataStore= LoginDataStore(context)
    val userEmail = dataStore.getEmail.collectAsState(initial = "")
    val userPass = dataStore.getPass.collectAsState(initial = "")
    var sEmail by rememberSaveable { mutableStateOf("") }
    var sPass by rememberSaveable { mutableStateOf("") }
    
    //Dummy Setup
    Log.d("Login Screen", "Login Screen Start")
    //Shared Scaffold - May not use in this screen
    Scaffold(backgroundColor = revBlue,
        topBar = { TopAppBar(backgroundColor = revOrange) {
            Text(text = "Login", fontSize = 18.sp, modifier = Modifier
                .padding(horizontal =5.dp), fontWeight = FontWeight.Medium
                , color = Color.White
            )
        }}
    )
    {
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center) {



            Card(
                shape = RoundedCornerShape(25.dp),
                elevation = 50.dp, modifier = Modifier.fillMaxSize(fraction = 0.9F)
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
                    Image(modifier=Modifier.size(120.dp),
                        painter = painterResource(id = R.drawable.rev_logo_2),
                        contentDescription = null)
                    Spacer(Modifier.size(40.dp))
                    TextField(
                        modifier = Modifier.padding(20.dp),
                        value = sEmail,
                        onValueChange = { sEmail = it },
                        label = { Text("Email: ") })
                    Spacer(Modifier.size(10.dp))
                    TextField(
                        modifier = Modifier.padding(20.dp),
                        value = sPass,
                        onValueChange = { sPass = it },
                        label = { Text("Password: ") })
                    Spacer(Modifier.size(30.dp))
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
                    // Extra dummy button to navigate
                    Button(
                        onClick =
                        {
                            //dummy navigation
                            navController.navigate(NavScreens.EditQuizTitle.route)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = revOrange)
                    )
                    {
                        Text(text = "Login (Create Quiz)",color= Color.White)
                    }
                   Text(text = "New user?: Register", fontSize = 15.sp, fontStyle = FontStyle.Italic,
                       modifier = Modifier.clickable {
                           navController.navigate(NavScreens.RegistrationScreen.route)
                       }, color = revBlue)
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