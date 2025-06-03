package com.rururi.easyprompt.ui.screen.prompt

enum class PromptStep(
    val number:Int,
    val displayName:String
) {
    Canvas(1, "Canvas"),
    Camera(2, "Camera"),
    Lighting(3, "Lighting"),
    Person(4, "Person"),
    Others(5, "Others"),
    Review(6, "Review")
}