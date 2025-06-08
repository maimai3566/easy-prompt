package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.prompt.PromptBottomBar
import com.rururi.easyprompt.ui.screen.prompt.PromptStep
import com.rururi.easyprompt.ui.screen.prompt.PromptType

class PromptBottomBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun firstStep_showsNextOnly() {
        composeTestRule.setContent {
            PromptBottomBar(
                currentStep = PromptStep.Canvas,
                promptType = PromptType.PERSON,
                onBack = {},
                onNext = {},
                onCopy = {},
                copied = false
            )
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.btn_back))
            .assertDoesNotExist()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.btn_next))
            .assertExists()
    }

    @Test
    fun reviewStep_showsCopyButton() {
        var copiedCalled = false
        composeTestRule.setContent {
            PromptBottomBar(
                currentStep = PromptStep.Review,
                promptType = PromptType.PERSON,
                onBack = {},
                onNext = {},
                onCopy = { copiedCalled = true },
                copied = false
            )
        }
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.btn_copy))
            .assertExists()
            .performClick()
        composeTestRule.runOnIdle { assertTrue(copiedCalled) }
    }
}

