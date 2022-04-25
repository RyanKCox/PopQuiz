package com.revature.popquiz

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.revature.popquiz.model.room.RoomDataManager
import com.revature.popquiz.model.room.quizroom.QuizRepository
import com.revature.popquiz.service.*
import com.revature.popquiz.ui.theme.PopQuizTheme
import com.revature.popquiz.view.navigation.StartNav
import com.revature.popquiz.viewmodels.QuizManager
import com.revature.popquiz.viewmodels.SplashScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//import dagger.hilt.android.AndroidEntryPoint
//
//
//@AndroidEntryPoint
class MainActivity : ComponentActivity()
{

    //Will change with Dagger-Hilt? -Evan
    private val splashScreenViewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        val app = this

        CoroutineScope(Dispatchers.IO).launch {

            val quizRepository = QuizRepository(app.application)
            RoomDataManager.quizRepository = quizRepository

            QuizManager.loadQuizzes()
        }
        setupAlarm()

        //Used to install and modify the Splash screen -Evan
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                splashScreenViewModel.isLoading.value
            }
        }

        setContent {


            //Create the navigation controller
            val navController = rememberNavController()


            PopQuizTheme()
            {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                )
                {
                    //Navigation Start
//                    PopQuizSettingsScreen(navController = navController)
                    StartNav(navController = navController)
                    //QuestionScreen()

                }
            }
        }
    }
    fun setupAlarm(){

        var waitTime:Long = 60_000* 30
        var startTime = System.currentTimeMillis()
        startTime += waitTime

        val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager

        val popIntent = Intent(this,AlarmReceiver::class.java)
        popIntent.putExtra(INTENT_COMMAND, INTENT_COMMAND_POPQUIZ)
        val pendingPop =
            PendingIntent.getBroadcast(this,0,popIntent,0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            startTime,
            waitTime,
            pendingPop
        )
    }
}