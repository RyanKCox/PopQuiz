package com.revature.popquiz.tests

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.revature.popquiz.MainActivity
import com.revature.popquiz.view.navigation.NavScreens
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun loginScreenTest() {
        composeTestRule.setContent { NavScreens.LoginScreen }
        val button = composeTestRule.onNode(hasTestTag("myTestTag"), useUnmergedTree = true)
        button.assertIsDisplayed()
        button.performClick()
    }
}