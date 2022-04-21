package com.revature.popquiz

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.revature.popquiz.ui.theme.PopQuizTheme
import com.revature.popquiz.view.navigation.StartNav
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTesting {

    // Generic test to see if the testing environment is set up right
    @Test
    fun addition() {
        1 + 1
        assertEquals(2, 2)
    }


    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

//    @get:Rule
//    val composeTestRule = createComposeRule()

    @get: Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    // Looks to see if the Login page can find "Login" text
    @Test
    fun twoLoginTextsOnLoginPageExists()
    {
        composeTestRule.onAllNodesWithText("Login")
    }


    // Looks to see if the Login page
    // has a button with text that says "Login" and is clickable
    @Test
    fun loginButtonExistsAndIsClickable()
    {
        composeTestRule
            .onAllNodesWithText("Login")
            .onFirst().assertHasClickAction()
    }

    // Still working on this
    @Test
    fun loginButtonTriggersLoginFunction()
    {
        composeTestRule.onAllNodesWithText("Login").onFirst().performClick()
    }

}

