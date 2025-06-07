package com.rururi.easyprompt.ui.screen.prompt


data class PromptUiState(
    val promptType: PromptType = PromptType.PERSON,
    val currentStep: PromptStep = PromptStep.Canvas,
    val canvasState: CanvasState = CanvasState(),
    val cameraState: CameraState = CameraState(),
    val themeSetState: ThemeSetState = ThemeSetState(),
    val lightState: LightingState = LightingState(),
    val personState: PersonState = PersonState(),
    val titleState: TitleState = TitleState(),
    val bodyState: BodyState = BodyState(),
    val result: String = "",
    val isEdit: Boolean = false,
)

data class TitleState(
    val text: String = "",
    val family: String = "",
    val size: String = "",
    val weight: String = "",
    val color: String = "",
    val xPos: String = "",
    val yPos: String = "",
    val height: String = "",
    val spacing: String = "",
)

data class BodyState(
    val text: String = "",
    val family: String = "",
    val size: String = "",
    val weight: String = "",
    val color: String = "",
    val xPos: String = "",
    val yPos: String = "",
    val height: String = "",
    val spacing: String = "",
)

data class CanvasState(
    val size: String = "",
    val type: String = "",
    val color: String = "",
    val color2: String = "",
    val effect: String = "",
    val padding: String = "",
    val overlay: String = "",
    val optType: String = "",
    val optEffect: String = "",
)

data class CameraState(
    val angle: String = "",
    val distance: String = "",
    val framing: String = "",
    val motion: String = "",
)

data class ThemeSetState(
    val tone: String = "",
    val keyword: String = "",
)

data class LightingState(
    val style: String = "",
    val direction: String = "",
    val color: String = "",
    val intensity: String = "",
    val contrast: String = "",
)

data class PersonState(
    val appearance: String = "",
    val emotion: String = "",
    val pose: String = "",
    val gaze: String = "",
    val position: String = "",
    val style: String = "",
    val resolution: String = "",
)



