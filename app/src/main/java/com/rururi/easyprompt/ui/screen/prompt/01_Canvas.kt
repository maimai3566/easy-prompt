package com.rururi.easyprompt.ui.screen.prompt

import android.R.attr.type
import android.R.id.message
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rururi.easyprompt.ui.theme.EasyPromptTheme
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ext.toColorOrDefault
import com.rururi.easyprompt.ext.toRgbaString
import com.rururi.easyprompt.ui.screen.ColorSelector
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.RadioSelector
import com.rururi.easyprompt.ui.screen.SetColor
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.SetRadioText
import com.rururi.easyprompt.utils.log

@Composable
fun CanvasSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
    ){
        item {
            val labelText = stringResource(R.string.option_custom)  //自分で設定
            val sizeOption = listOf(
                RadioOption(R.string.canvas_size_1),
                RadioOption(R.string.canvas_size_2),
                RadioOption(R.string.canvas_size_3),
            )
            SetRadio(
                title = "キャンバスの大きさ",
                options = sizeOption,
                selectedOption = uiState.canvasState.size,
                onOptionSelected = {
                    viewModel.updateUiState {
                        copy(
                            canvasState = canvasState.copy(
                                size = it
                            )
                        )
                    }
                }
            )

            val typeOption = listOf(
                RadioOption(R.string.canvas_type_1),
                RadioOption(R.string.canvas_type_2),
                RadioOption(R.string.canvas_type_3),
                RadioOption(R.string.canvas_type_4),
                RadioOption(R.string.option_custom)
            )
            val labelTransparent = stringResource(R.string.canvas_type_1)   //透明
            val labelGradient = stringResource(R.string.canvas_type_3)   //グラデーション
            SetRadioText(
                title = "背景の種類",
                options = typeOption,
                selectedOption = uiState.canvasState.optType,
                onOptionSelected = {
                    viewModel.updateUiState {
                        copy(
                            canvasState = canvasState.copy(
                                optType = it,
                                type = if (it != labelText) it else uiState.canvasState.type,   //自分で設定以外の時入力フィールドにオプション値をセット
                                color = if (it == labelTransparent) "" else uiState.canvasState.color,  //透明を選択したときカラーをリセット
                                color2 = if (it != labelGradient) "" else uiState.canvasState.color2,  //グラデーション以外を選択したときカラー2をリセット
                                effect = if (it == labelTransparent) "" else uiState.canvasState.effect,  //透明を選択したときエフェクトをリセット
                                optEffect = if (it == labelTransparent) "" else uiState.canvasState.optEffect,  //透明を選択したときエフェクトオプションをリセット
                                padding = if (it == labelTransparent) "" else uiState.canvasState.padding,  //透明を選択したときパディングをリセット
                            )
                        )
                    }
                },
                customExample = "新緑の木々が美しい森の中にぽっかり空いた広い空間",
                customText = uiState.canvasState.type,
                customOnValueChange = {
                    viewModel.updateUiState({
                        copy(
                            canvasState = canvasState.copy(
                                type = it
                            )
                        )
                    })
                },
            )

            //背景の種類を透明にしたときは、項目が消えるよう条件分岐
            if (uiState.canvasState.optType != stringResource(R.string.canvas_type_1)) {
                Row {
                    SetColor(
                        title = "背景の色",
                        onColorSelected = {
                            viewModel.updateUiState {
                                copy(
                                    canvasState = canvasState.copy(
                                        color = it.toRgbaString()
                                    )
                                )
                            }
                        },
                        selectedColor = uiState.canvasState.color.toColorOrDefault(),
                    )
                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_small)))
                    //グラデーションを選択したときだけ、もう1色選択できるようにする
                    if (uiState.canvasState.optType == stringResource(R.string.canvas_type_3)) {
                        SetColor(
                            title = "2色目",
                            onColorSelected = {
                                viewModel.updateUiState {
                                    copy(
                                        canvasState = canvasState.copy(
                                            color2 = it.toRgbaString()
                                        )
                                    )
                                }
                            },
                            selectedColor = uiState.canvasState.color2.toColorOrDefault(),
                        )
                    }
                }

                val effectOption = listOf(
                    RadioOption(R.string.canvas_effect_1),
                    RadioOption(R.string.canvas_effect_2),
                    RadioOption(R.string.canvas_effect_3),
                    RadioOption(R.string.canvas_effect_4),
                    RadioOption(R.string.option_custom)
                )
                SetRadioText(
                    title = "背景のエフェクト",
                    options = effectOption,
                    selectedOption = uiState.canvasState.optEffect,
                    onOptionSelected = {
                        viewModel.updateUiState {
                            copy(
                                canvasState = canvasState.copy(
                                    optEffect = it,
                                    effect = if (it != labelText) it else uiState.canvasState.effect,
                                )
                            )
                        }
                    },
                    customExample = "キラキラ輝く",
                    customText = uiState.canvasState.effect,
                    customOnValueChange = {
                        viewModel.updateUiState({
                            copy(
                                canvasState = canvasState.copy(
                                    effect = it
                                )
                            )
                        })
                    },
                )

                val paddingOption = listOf(
                    RadioOption(R.string.canvas_padding_1),
                    RadioOption(R.string.canvas_padding_2),
                    RadioOption(R.string.canvas_padding_3),
                )
                SetRadio(
                    title = "キャンバスの余白",
                    options = paddingOption,
                    selectedOption = uiState.canvasState.padding,
                    onOptionSelected = {
                        viewModel.updateUiState {
                            copy(
                                canvasState = canvasState.copy(
                                    padding = it
                                )
                            )
                        }
                    }
                )

                val overlayOption = listOf(
                    RadioOption(R.string.canvas_overlay_1),
                    RadioOption(R.string.canvas_overlay_2),
                    RadioOption(R.string.canvas_overlay_3),
                )
                SetRadio(
                    title = "オーバーレイ",
                    options = overlayOption,
                    selectedOption = uiState.canvasState.overlay,
                    onOptionSelected = { viewModel.updateUiState { copy(canvasState = canvasState.copy(overlay = it)) }}
                )
                Text("※テクスチャまたは画像を選択した場合、別途画像を用意する必要があります")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Step1Preview() {
    EasyPromptTheme {
        CanvasSec(viewModel = PromptViewModel(), uiState = PromptUiState())
    }
}