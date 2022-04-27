package com.revature.popquiz.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.revature.popquiz.ui.theme.PopQuizTheme
import com.revature.popquiz.view.screens.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testButtonClick() {
        composeTestRule.setContent {
            PopQuizTheme {
                LoginScreen(navController = rememberNavController())
            }
        }
        val button = composeTestRule.onNode(hasTestTag("buttonTestTag"), true)
        button.assertIsDisplayed()
        button.performClick()
    }
}