package com.rururi.easyprompt

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.testing.TestNavHostController
import com.rururi.easyprompt.ui.screen.prompt.*
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class PromptScreenTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var viewModel: PromptViewModel

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        hiltRule.inject()
        composeTestRule.setContent {
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

