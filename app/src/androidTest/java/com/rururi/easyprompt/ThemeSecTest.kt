package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.prompt.ThemeSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.PromptUiState

class ThemeSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ThemeSec(viewModel = PromptViewModel(), uiState = PromptUiState())
        }
    }

    @Test
    fun displaysThemeOptions() {
        composeTestRule.onNodeWithText("テーマのトーン・ムード").assertExists()
        composeTestRule.onNodeWithText("キーワード(3,4個程度)").assertExists()
    }
}
