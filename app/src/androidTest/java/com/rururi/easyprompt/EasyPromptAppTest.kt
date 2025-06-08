package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.EasyPromptApp
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel

class EasyPromptAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(composeTestRule.activity)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            EasyPromptApp(promptViewModel = PromptViewModel())
        }
    }

    @Test
    fun navigateToPromptScreen_showsBars() {
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_prompt)
        ).performClick()
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
    }

    @Test
    fun homeButton_returnsToHome() {
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_prompt)
        ).performClick()
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.sc_home)
        ).performClick()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_prompt)
        ).assertExists()
    }
}

