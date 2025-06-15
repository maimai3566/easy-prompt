package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.prompt.PersonSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.PromptUiState

class PersonSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PersonSec(viewModel = PromptViewModel(), uiState = PromptUiState())
        }
    }

    @Test
    fun displaysPersonFields() {
        composeTestRule.onNodeWithText("外見").assertExists()
        composeTestRule.onNodeWithText("表情").assertExists()
        composeTestRule.onNodeWithText("ポーズ").assertExists()
    }
}
