package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ext.toColorOrDefault
import com.rururi.easyprompt.ext.toRgbaString
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetColor
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.SetRadioText
import com.rururi.easyprompt.ui.theme.EasyPromptTheme


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
                RadioOption(R.string.canvas_size_1,"canvasSize1"),
                RadioOption(R.string.canvas_size_2,"canvasSize2"),
                RadioOption(R.string.canvas_size_3,"canvasSize3"),
            )
            SetRadio(
                title = stringResource(R.string.canvas_size_title),
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
                RadioOption(R.string.option_custom, tag = "canvasTypeCustom")
            )
            val labelTransparent = stringResource(R.string.canvas_type_1)   //透明
            val labelGradient = stringResource(R.string.canvas_type_3)   //グラデーション
            SetRadioText(
                title = stringResource(R.string.canvas_type_title),
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
                example = stringResource(R.string.canvas_type_example),
                value = uiState.canvasState.type,
                onValueChange = {
                    viewModel.updateUiState({
                        copy(
                            canvasState = canvasState.copy(
                                type = it
                            )
                        )
                    })
                },
                placeholderId = R.string.canvas_type_placeholder,
            )

            //背景の種類を透明にしたときは、項目が消えるよう条件分岐
            if (uiState.canvasState.optType != stringResource(R.string.canvas_type_1)) {
                Row {
                    SetColor(
                        title = stringResource(R.string.canvas_color_title),
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
//                        modifier = Modifier.testTag("mainColorButton")
                    )
                    Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_small)))
                    //グラデーションを選択したときだけ、もう1色選択できるようにする
                    if (uiState.canvasState.optType == stringResource(R.string.canvas_type_3)) {
                        SetColor(
                            title = "",
                            showTitle = false,
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
                            modifier = Modifier.testTag("subColorButton")
                        )
                    }
                }

                val effectOption = listOf(
                    RadioOption(R.string.canvas_effect_1),
                    RadioOption(R.string.canvas_effect_2),
                    RadioOption(R.string.canvas_effect_3),
                    RadioOption(R.string.canvas_effect_4),
                    RadioOption(R.string.option_custom, tag = "canvasEffectCustom")
                )
                SetRadioText(
                    title = stringResource(R.string.canvas_effect_title),
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
                    example = "キラキラ輝く",
                    value = uiState.canvasState.effect,
                    onValueChange = {
                        viewModel.updateUiState({
                            copy(
                                canvasState = canvasState.copy(
                                    effect = it
                                )
                            )
                        })
                    },
                    placeholderId = R.string.canvas_effect_placeholder,
                )

                val paddingOption = listOf(
                    RadioOption(R.string.canvas_padding_1),
                    RadioOption(R.string.canvas_padding_2),
                    RadioOption(R.string.canvas_padding_3),
                )
                SetRadio(
                    title = stringResource(R.string.canvas_padding_title),
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
                    title = stringResource(R.string.canvas_overlay_title),
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