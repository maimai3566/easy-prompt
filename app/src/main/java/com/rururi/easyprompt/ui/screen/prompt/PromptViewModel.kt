package com.rururi.easyprompt.ui.screen.prompt

import androidx.lifecycle.ViewModel
import com.rururi.easyprompt.utils.log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.temporal.TemporalAdjusters.next

class PromptViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(PromptUiState())
    val uiState: StateFlow<PromptUiState> = _uiState.asStateFlow()

    //汎用の更新関数
    fun updateUiState(update: PromptUiState.() -> PromptUiState) {
        _uiState.update { it.update() }
    }

    fun nextStep() {
        val next = when (_uiState.value.currentStep) {
            PromptStep.Canvas -> PromptStep.Camera
            PromptStep.Camera -> PromptStep.Lighting
            PromptStep.Lighting -> PromptStep.Person
            PromptStep.Person -> PromptStep.Others
            PromptStep.Others -> PromptStep.Review
            PromptStep.Review -> PromptStep.Review  //ここで終わり
        }
        _uiState.update {
            it.copy(currentStep = next)
        }
    }
    fun prevStep() {
        val prev = when (_uiState.value.currentStep) {
            PromptStep.Review -> PromptStep.Others
            PromptStep.Others -> PromptStep.Person
            PromptStep.Person -> PromptStep.Lighting
            PromptStep.Lighting -> PromptStep.Camera
            PromptStep.Camera -> PromptStep.Canvas
            PromptStep.Canvas -> PromptStep.Canvas
        }
        _uiState.update {
            it.copy(currentStep = prev)
        }
    }
}