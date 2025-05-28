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
        PromptStep.Canvas -> Step1(onNext = { viewModel.nextStep() })
//        PromptStep.Camera -> CameraScreen()
//        PromptStep.Lighting -> LightingScreen()
//        PromptStep.Person -> PersonScreen()
//        PromptStep.Others -> OthersScreen()
    }
    Text(text = stringResource(R.string.btn_prompt))

}