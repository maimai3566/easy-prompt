package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.prompt.LightingSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.PromptUiState

class LightingSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            LightingSec(viewModel = PromptViewModel(), uiState = PromptUiState())
        }
    }

    @Test
    fun displaysLightingOptions() {
        composeTestRule.onNodeWithText("ライティングスタイル").assertExists()
        composeTestRule.onNodeWithText("光の方向").assertExists()
        composeTestRule.onNodeWithText("光の色").assertExists()
        composeTestRule.onNodeWithText("■光の強度").assertExists()
        composeTestRule.onNodeWithText("■光のコントラスト").assertExists()
    }
}
