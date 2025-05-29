package com.rururi.easyprompt.ui.screen.prompt

sealed class PromptStep(
    val number:Int,
    val displayName:String
) {
    object Canvas : PromptStep(1, "Canvas")
    object Camera : PromptStep(2, "Camera")
    object Lighting : PromptStep(3, "Lighting")
    object Person : PromptStep(4, "Person")
    object Others : PromptStep(5, "Others")
    object Review : PromptStep(6, "Review")

    companion object {
        val all = listOf(Canvas, Camera, Lighting, Person, Others, Review)
    }
}