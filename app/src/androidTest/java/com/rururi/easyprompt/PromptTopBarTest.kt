package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.prompt.PromptTopBar
import com.rururi.easyprompt.ui.screen.prompt.PromptStep

class PromptTopBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private var homeClicked = false

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PromptTopBar(
                currentStep = PromptStep.Canvas,
                onHome = { homeClicked = true }
            )
        }
    }

    @Test
    fun titleIsDisplayed() {
        composeTestRule.onNodeWithText("★ ${PromptStep.Canvas.displayName}")
            .assertExists()
    }

    @Test
    fun homeButtonCallsCallback() {
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.sc_home)
        ).performClick()
        composeTestRule.runOnIdle {
            assertTrue(homeClicked)
        }
    }
}

