package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun PromptScreen(
    navController: NavController,
    promptType: PromptType,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    val currentStep = uiState.currentStep

    when (currentStep) {
        PromptStep.Canvas -> CanvasSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Camera -> CameraSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Theme -> ThemeSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Person -> PersonSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Lighting -> LightingSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Title -> TitleSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Body -> BodySec(viewModel=viewModel, uiState = uiState)
        PromptStep.Review -> ReviewSec(viewModel=viewModel, uiState = uiState)
    }
}