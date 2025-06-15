package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.prompt.CanvasSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.PromptUiState

class CanvasSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            CanvasSec(viewModel = PromptViewModel(), uiState = PromptUiState())
        }
    }

    @Test
    fun displaysCanvasOptions() {
        composeTestRule.onNodeWithText("キャンバスの大きさ").assertExists()
        composeTestRule.onNodeWithText("背景の種類").assertExists()
    }
}
