package com.rururi.easyprompt.ui.screen.prompt

enum class PromptStep(
    val number:Int,
    val displayName:String
) {
    Canvas(1, "Canvas"),
    Camera(2, "Camera"),
    Theme(3,"Theme"),
    Person(4, "Person"),
    Lighting(5, "Lighting"),
    Title(6, "Title"),
    Body(7, "Body"),
    Review(99, "Review")
}

//選択したプロンプトタイプ
enum class PromptType{
    PERSON, //人物中心
    TEXT,   //文字中心
    BACKGROUND,  //背景のみ
}

//選択に応じてルートをマッピング
val promptStepMap: Map<PromptType, List<PromptStep>> = mapOf(
    PromptType.PERSON to listOf(
        PromptStep.Canvas,
        PromptStep.Camera,
        PromptStep.Theme,
        PromptStep.Lighting,
        PromptStep.Person,
        PromptStep.Review,
    ),
    PromptType.TEXT to listOf(
        PromptStep.Canvas,
        PromptStep.Camera,
        PromptStep.Theme,
        PromptStep.Title,
        PromptStep.Body,
        PromptStep.Review,
    ),
    PromptType.BACKGROUND to listOf(
        PromptStep.Canvas,
        PromptStep.Camera,
        PromptStep.Theme,
        PromptStep.Lighting,
        PromptStep.Review,
    )
)
