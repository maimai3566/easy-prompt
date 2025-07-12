package com.rururi.easyprompt

import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import com.rururi.easyprompt.ui.screen.prompt.PromptScreen
import com.rururi.easyprompt.ui.screen.prompt.PromptStep
import com.rururi.easyprompt.ui.screen.prompt.PromptType
import com.rururi.easyprompt.ui.screen.prompt.PromptUiState
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PromptScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var viewModel: PromptViewModel
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            navController = TestNavHostController(composeTestRule.activity)
            PromptScreen(
                navController = navController,
                promptType = PromptType.PERSON,
                viewModel = viewModel,
                uiState = PromptUiState(currentStep = PromptStep.Camera)
            )
        }
    }

    @Test
    fun cameraStep_displaysCameraSection() {
        composeTestRule.onNodeWithText("■カメラの角度").assertExists()
    }
}

