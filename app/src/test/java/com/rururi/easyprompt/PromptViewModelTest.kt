package com.rururi.easyprompt

import com.rururi.easyprompt.ui.screen.prompt.PromptStep
import com.rururi.easyprompt.ui.screen.prompt.PromptType
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
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
        assertEquals(PromptStep.Theme, viewModel.uiState.value.currentStep)

        viewModel.nextStep()
        assertEquals(PromptStep.Lighting, viewModel.uiState.value.currentStep)

        viewModel.nextStep()
        assertEquals(PromptStep.Person, viewModel.uiState.value.currentStep)

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
        assertEquals(PromptStep.Person, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Lighting, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Theme, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Camera, viewModel.uiState.value.currentStep)

        viewModel.prevStep()
        assertEquals(PromptStep.Canvas, viewModel.uiState.value.currentStep)

        // further prevStep should stay on Canvas
        viewModel.prevStep()
        assertEquals(PromptStep.Canvas, viewModel.uiState.value.currentStep)
    }

    @Test
    fun stepList_returnsCorrectStepsForEachPromptType() {
        val viewModel = PromptViewModel()

        viewModel.updateUiState { copy(promptType = PromptType.PERSON) }
        assertEquals(
            listOf(
                PromptStep.Canvas,
                PromptStep.Camera,
                PromptStep.Theme,
                PromptStep.Lighting,
                PromptStep.Person,
                PromptStep.Review
            ),
            viewModel.stepList()
        )

        viewModel.updateUiState { copy(promptType = PromptType.TEXT) }
        assertEquals(
            listOf(
                PromptStep.Canvas,
                PromptStep.Camera,
                PromptStep.Theme,
                PromptStep.Title,
                PromptStep.Body,
                PromptStep.Review
            ),
            viewModel.stepList()
        )

        viewModel.updateUiState { copy(promptType = PromptType.BACKGROUND) }
        assertEquals(
            listOf(
                PromptStep.Canvas,
                PromptStep.Camera,
                PromptStep.Theme,
                PromptStep.Lighting,
                PromptStep.Review
            ),
            viewModel.stepList()
        )
    }

    @Test
    fun reset_setsDefaultPromptTypeAndStep() {
        val viewModel = PromptViewModel()
        viewModel.updateUiState {
            copy(promptType = PromptType.TEXT, currentStep = PromptStep.Body)
        }

        viewModel.reset()

        assertEquals(PromptType.PERSON, viewModel.uiState.value.promptType)
        assertEquals(PromptStep.Canvas, viewModel.uiState.value.currentStep)
    }

    @Test
    fun buildYaml_generatesExpectedString() {
        val viewModel = PromptViewModel()

        viewModel.updateUiState {
            copy(
                canvasState = canvasState.copy(size = "1920x1080", type = "solid"),
                cameraState = cameraState.copy(angle = "wide"),
                themeSetState = themeSetState.copy(tone = "dark"),
                personState = personState.copy(appearance = "tall"),
                lightState = lightState.copy(style = "soft"),
                titleState = titleState.copy(text = "Hello"),
                bodyState = bodyState.copy(text = "World")
            )
        }

        val expected = """
            |prompt:
            |  canvas:
            |    size: "1920x1080"
            |    background:
            |      type: "solid"
            |  camera:
            |    angle: "wide"
            |  theme:
            |    tone: "dark"
            |  person:
            |    appearance: "tall"
            |  lighting:
            |    style: "soft"
            |  title:
            |    text: "Hello"
            |  body:
            |    text: "World"
            |""".trimMargin()

        println("---Expected---\n$expected")
        println("---Actual---\n${viewModel.buildYaml()}")

        assertEquals(expected, viewModel.buildYaml())
    }
}
