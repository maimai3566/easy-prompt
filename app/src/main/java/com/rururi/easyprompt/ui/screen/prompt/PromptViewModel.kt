package com.rururi.easyprompt.ui.screen.prompt

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PromptViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(PromptUiState())
    val uiState: StateFlow<PromptUiState> = _uiState.asStateFlow()

    //汎用の更新関数
    fun updateUiState(update: PromptUiState.() -> PromptUiState) {
        _uiState.update { it.update() }
    }

    //現在のプロンプトタイプに応じたステップをリスト化
    fun stepList(): List<PromptStep> {
        return promptStepMap[_uiState.value.promptType] ?: emptyList()
    }

    //現在のプロンプトを全部初期化
    fun resetAll() {
        _uiState.value = PromptUiState()    //全状態を初期化
    }

    //現在のプロンプトタイプ、現在のステップなどを初期化（リセット）
    fun reset() {
        val defaultType = PromptType.PERSON
        val firstStep = promptStepMap[defaultType]?.firstOrNull() ?: PromptStep.Canvas
        _uiState.update {
            it.copy(
                promptType = defaultType,
                currentStep = firstStep
            )
        }
    }
    fun nextStep() {
        val steps = stepList()
        val current = _uiState.value.currentStep    //現在のステップ
        val currentIndex = steps.indexOf(current)    //現在のステップのインデックス

        //次のステップがReviewならgeneratePromptTextを実行
        if(currentIndex + 1 < steps.size && steps[currentIndex + 1] == PromptStep.Review) {
            val yaml = buildYaml()  //yamlを構築
            _uiState.update {
                it.copy(result = yaml)
            }
        }

        // インデックスが次の要素を指せる場合のみ進める
        if (currentIndex != -1 && currentIndex + 1 < steps.size) {
            val next = steps[currentIndex + 1]
            _uiState.update {
                it.copy(currentStep = next)
            }
        }
    }

    fun prevStep() {
        val steps = stepList()
        val current = _uiState.value.currentStep    //現在のステップ
        val currentIndex = steps.indexOf(current)    //現在のステップのインデックス

        if (currentIndex > 0) {     //インデックスが0より大きい時だけ戻れる
            val prev = steps[currentIndex - 1]
            _uiState.update {
                it.copy(currentStep = prev)
            }
        }
    }

    //追加したYAML文がブランクだったら追加しない拡張関数
    fun String.toYamlLine(key:String, indent: Int = 4):String? =
        takeIf { it.isNotEmpty() }?.let{ " ".repeat(indent) + "$key: \"$it\""}

    //YAML出力
    fun buildYaml(): String {
        val sb = StringBuilder()    //文字列をどんどん追加して組み立てるクラス
        val state = _uiState.value

        sb.appendLine("prompt:")
        //canvasだけbackgroundがあるので個別処理
        val canvasSizeLine = state.canvasState.size.toYamlLine("size")
        val canvasBackgroundLines = listOfNotNull(
            state.canvasState.type.toYamlLine("type",6),
            state.canvasState.color.toYamlLine("color",6),
            state.canvasState.color2.toYamlLine("color2",6),
            state.canvasState.effect.toYamlLine("effect",6),
            state.canvasState.padding.toYamlLine("padding",6),
            state.canvasState.overlay.toYamlLine("overlay",6),
        )
        val hasCanvasData = !canvasSizeLine.isNullOrBlank() || canvasBackgroundLines.isNotEmpty()
        if (hasCanvasData) {
            sb.appendLine("  canvas:")
            canvasSizeLine?.let{ sb.appendLine(it) }

            if (canvasBackgroundLines.isNotEmpty()) {
                sb.appendLine("    background:")
                canvasBackgroundLines.forEach { sb.appendLine(it) }
            }
        }

        val cameraLines = listOfNotNull(
            state.cameraState.angle.toYamlLine("angle"),
            state.cameraState.distance.toYamlLine("distance"),
            state.cameraState.framing.toYamlLine("framing"),
            state.cameraState.motion.toYamlLine("motion"),
        )
        if (cameraLines.isNotEmpty()) {
            sb.appendLine("  camera:")
            cameraLines.forEach { sb.appendLine(it) }
        }

        val themeLines = listOfNotNull(
            state.themeSetState.tone.toYamlLine("tone"),
            state.themeSetState.keyword.toYamlLine("keyword"),
        )
        if (themeLines.isNotEmpty()) {
            sb.appendLine("  theme:")
            themeLines.forEach { sb.appendLine(it) }
        }

        val personLines = listOfNotNull(
            state.personState.appearance.toYamlLine("appearance"),
            state.personState.emotion.toYamlLine("emotion"),
            state.personState.pose.toYamlLine("pose"),
            state.personState.gaze.toYamlLine("gaze"),
            state.personState.position.toYamlLine("position"),
            state.personState.style.toYamlLine("style"),
            state.personState.resolution.toYamlLine("resolution"),
        )
        if (personLines.isNotEmpty()) {
            sb.appendLine("  person:")
            personLines.forEach { sb.appendLine(it) }
        }

        val lightingLines = listOfNotNull(
            state.lightState.style.toYamlLine("style"),
            state.lightState.direction.toYamlLine("direction"),
            state.lightState.color.toYamlLine("color"),
            state.lightState.intensity.toYamlLine("intensity"),
            state.lightState.contrast.toYamlLine("contrast"),
        )
        if (lightingLines.isNotEmpty()) {
            sb.appendLine("  light:")
            lightingLines.forEach { sb.appendLine(it) }
        }

        val titleLines = listOfNotNull(
            state.titleState.text.toYamlLine("text"),
            state.titleState.family.toYamlLine("family"),
            state.titleState.size.toYamlLine("size"),
            state.titleState.weight.toYamlLine("weight"),
            state.titleState.color.toYamlLine("color"),
            state.titleState.xPos.toYamlLine("xPos"),
            state.titleState.yPos.toYamlLine("yPos"),
            state.titleState.height.toYamlLine("height"),
            state.titleState.spacing.toYamlLine("spacing"),
        )
        if (titleLines.isNotEmpty()) {
            sb.appendLine("  title:")
            titleLines.forEach { sb.appendLine(it) }
        }

        val bodyLines = listOfNotNull(
            state.bodyState.text.toYamlLine("text"),
            state.bodyState.family.toYamlLine("family"),
            state.bodyState.size.toYamlLine("size"),
            state.bodyState.weight.toYamlLine("weight"),
            state.bodyState.color.toYamlLine("color"),
            state.bodyState.xPos.toYamlLine("xPos"),
            state.bodyState.yPos.toYamlLine("yPos"),
            state.bodyState.height.toYamlLine("height"),
            state.bodyState.spacing.toYamlLine("spacing"),
        )
        if (bodyLines.isNotEmpty()) {
            sb.appendLine("  body:")
            bodyLines.forEach { sb.appendLine(it) }
        }

        return sb.toString()
    }
}