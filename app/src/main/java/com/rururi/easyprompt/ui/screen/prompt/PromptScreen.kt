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
        PromptStep.Camera -> Step2(onNext = { viewModel.nextStep() })
        PromptStep.Lighting -> Step3(onNext = { viewModel.nextStep() })
        PromptStep.Person -> Step4(onNext = { viewModel.nextStep() })
        PromptStep.Others -> Step5(onNext = { viewModel.nextStep() })
        PromptStep.Review -> Step6(onNext = { viewModel.nextStep() })
    }
}