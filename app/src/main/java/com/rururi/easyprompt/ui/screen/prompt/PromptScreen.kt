package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import com.rururi.easyprompt.R
import androidx.compose.runtime.getValue

@Composable
fun PromptScreen(
    viewModel: PromptViewModel
) {
    val uiState: PromptUiState by viewModel.uiState.collectAsState()
    when (uiState.currentStep) {
        PromptStep.Canvas -> CanvasSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Camera -> CameraSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Lighting -> LightingSec(viewModel = viewModel, uiState = uiState)
        PromptStep.Person -> PersonSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Others -> TitleSec(viewModel=viewModel, uiState = uiState)
        PromptStep.Review -> Step6()
    }
}