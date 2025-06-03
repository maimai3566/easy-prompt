package com.rururi.easyprompt

import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.PromptStep
import org.junit.Assert.assertEquals
import org.junit.Test

class PromptViewModelTest {
    @Test
    fun nextStep_movesThroughSteps() {
        val viewModel = PromptViewModel()
        // initial step is Canvas
        assertEquals(PromptStep.Canvas, viewModel.uiState.value.currentStep)

        viewModel.nextStep()
        assertEquals(PromptStep.Camera, viewModel.uiState.value.currentStep)

        viewModel.nextStep()
        assertEquals(PromptStep.Lighting, viewModel.uiState.value.currentStep)

        viewModel.nextStep()
        assertEquals(PromptStep.Person, viewModel.uiState.value.currentStep)

        viewModel.nextStep()
        assertEquals(PromptStep.Others, viewModel.uiState.value.currentStep)

        viewModel.nextStep()
        assertEquals(PromptStep.Review, viewModel.uiState.value.currentStep)

        // further nextStep should stay on Review
        viewModel.nextStep()
        assertEquals(PromptStep.Review, viewModel.uiState.value.currentStep)
    }

    @Test
    fun prevStep_movesBackThroughSteps() {
        val viewModel = PromptViewModel()
        repeat(5) { viewModel.nextStep() } // move to Review
        assertEquals(PromptStep.Review, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Others, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Person, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Lighting, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Camera, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Canvas, viewModel.uiState.value.currentStep)

        // further prevStep should stay on Canvas
        viewModel.prevStep()
        assertEquals(PromptStep.Canvas, viewModel.uiState.value.currentStep)
    }
}
