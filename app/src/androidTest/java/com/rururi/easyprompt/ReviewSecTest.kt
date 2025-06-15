package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import com.rururi.easyprompt.ui.screen.prompt.ReviewSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.PromptUiState

class ReviewSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            ReviewSec(viewModel = PromptViewModel(), uiState = PromptUiState())
        }
    }

    @Test
    fun displaysPreviewTitle_andToggleEdit() {
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.preview_title)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_edit)
        ).performClick()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_edited)
        ).assertExists()
    }
}
