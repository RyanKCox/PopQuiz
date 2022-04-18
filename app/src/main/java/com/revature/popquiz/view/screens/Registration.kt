package com.revature.popquiz

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.model.datastore.LoginDataStore
import com.revature.popquiz.ui.theme.revBlue
import com.revature.popquiz.ui.theme.revOrange
import com.revature.popquiz.view.navigation.NavScreens
import kotlinx.coroutines.launch
//import javax.inject.Inject

@Composable
fun Register(navController: NavController)
{

    val scaffoldState = rememberScaffoldState()
    val context= LocalContext.current
    val scope = rememberCoroutineScope()

    val dataStore= LoginDataStore(context)

    Scaffold (
        backgroundColor = revBlue,
        topBar= {TopAppBar(backgroundColor = revOrange) {
            Text(text = "Login", fontSize = 18.sp, modifier = Modifier
                .padding(horizontal =5.dp), fontWeight = FontWeight.Medium, color = Color.White
            )
        }},
        scaffoldState = scaffoldState,
        content =
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center)
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
                        modifier = Modifier.fillMaxSize(fraction = 0.9F),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    )
                    {
                        var sName by rememberSaveable { mutableStateOf("") }
                        var sPass by rememberSaveable { mutableStateOf("") }
                        var sPassConfirm by rememberSaveable { mutableStateOf("") }
                        var sEmail by rememberSaveable { mutableStateOf("") }

                        Spacer(Modifier.size(10.dp))
                        Text(text = "Create New Account", fontSize = 30.sp,
                            fontWeight = FontWeight.Medium)
                        Spacer(Modifier.size(20.dp))

                        TextField(
                            modifier= Modifier.padding(20.dp) ,
                            value = sEmail,
                            onValueChange = { sEmail = it },
                            label = { Text("Email: ") })

                        Spacer(modifier = Modifier.size(10.dp))

                        TextField(
                            modifier= Modifier.padding(20.dp) ,
                            value = sPass,
                            onValueChange = { sPass = it },
                            label = { Text("Password: ") })

                        Spacer(modifier = Modifier.size(10.dp))

                        TextField(
                            modifier= Modifier.padding(20.dp) ,
                            value = sPassConfirm,
                            onValueChange = { sPassConfirm = it },
                            label = { Text("Confirm Password: ") })

                        Spacer(modifier = Modifier.size(10.dp))

                        TextField(
                            modifier= Modifier.padding(20.dp) ,
                            value = sName,
                            onValueChange = { sName = it },
                            label = { Text("Name: ") })


                        Spacer(modifier = Modifier.size(20.dp))

                        Button(
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = revOrange),
                            onClick = {
                                if (checkParams(sName,sPass,sEmail,sPassConfirm)&& checkMatch(sPass,sPassConfirm)){
                                    scope.launch {
                                        dataStore.saveEmail(sEmail)
                                        dataStore.saveName(sName)
                                        dataStore.savePassword(sPass)
                                        navController.navigate(NavScreens.LoginScreen.route)
                                    }

                                }
                                else
                                {
                                    if (checkMatch(sPass,sPassConfirm))
                                    {
                                        scope.launch {
                                            Toast.makeText(
                                            context,
                                            "Fields not completed correctly",
                                            Toast.LENGTH_SHORT
                                        ).show() }

                                    }
                                    else
                                    {
                                        Toast.makeText(
                                            context,
                                            "Password inputs do not match",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                }

                            }
                        )
                        {
                            Text(text = "Register")
                        }
                    }
                }
            }

        }
    )


}
fun checkParams(a:String,b:String,c:String,d:String):Boolean
{
    return a.isNotEmpty() && b.isNotEmpty() && c.isNotEmpty() && d.isNotEmpty()

}
fun checkMatch(a: String,b: String)=a.equals(b)

@Composable
@Preview
fun regPreview()
{
    val navController = rememberNavController()
    Register(navController)

}