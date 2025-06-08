package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.HomeScreen

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var personClicked = false
    private var textClicked = false
    private var backgroundClicked = false
    private var resetCalled = false

    @Before
    fun setUp() {
        composeTestRule.setContent {
            HomeScreen(
                onNavigateToPerson = { personClicked = true },
                onNavigateToText = { textClicked = true },
                onNavigateToBackground = { backgroundClicked = true },
                onResetAll = { resetCalled = true }
            )
        }
    }

    @Test
    fun buttons_triggerCallbacks() {
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_prompt)
        ).performClick()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_text)
        ).performClick()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_background)
        ).performClick()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_full_reset)
        ).performClick()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.dialog_reset)
        ).performClick()
        composeTestRule.runOnIdle {
            assertTrue(personClicked)
            assertTrue(textClicked)
            assertTrue(backgroundClicked)
            assertTrue(resetCalled)
        }
    }
}

