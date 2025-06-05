package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ext.toColorOrDefault
import com.rururi.easyprompt.ext.toRgbaString
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetColor
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.SetRadioText
import com.rururi.easyprompt.ui.screen.SetTextField
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun TitleSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
    ) {
        SetTextField(
            title = "タイトルを決めてください",
            example = "",
            value = uiState.titleState.text,
            onValueChange = { viewModel.updateUiState({ copy(titleState = titleState.copy(text = it)) }) },
            placeholderId = R.string.title_text,
        )
        val familyOption= listOf(
            RadioOption(R.string.title_family_1),
            RadioOption(R.string.title_family_2),
            RadioOption(R.string.title_family_3),
            RadioOption(R.string.title_family_4),
            RadioOption(R.string.title_family_5),
            RadioOption(R.string.option_custom),
        )
        SetRadioText(
            title = "タイトルのフォント",
            options = familyOption,
            selectedOption = uiState.titleState.family,
            onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(family = it)) }) },
            example = "お気に入りのフォントを入力",
            value = uiState.titleState.family,
            onValueChange = { viewModel.updateUiState({ copy(titleState = titleState.copy(family = it)) }) },
            placeholderId = R.string.title_placeholder,
        )
        val sizeOption=listOf(
            RadioOption(R.string.title_size_1),
            RadioOption(R.string.title_size_2),
            RadioOption(R.string.title_size_3),
        )
        SetRadio(
            title = "タイトルのサイズ",
            options = sizeOption,
            selectedOption = uiState.titleState.size,
            onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(size = it)) }) },
        )

        val weightOption=listOf(
            RadioOption(R.string.title_weight_1),
            RadioOption(R.string.title_weight_2),
        )
        SetRadio(
            title = "タイトルの太さ",
            options = weightOption,
            selectedOption = uiState.titleState.weight,
            onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(weight = it)) }) }
        )
        SetColor(
            title = "タイトルの色",
            onColorSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(color = it.toRgbaString())) }) },
            selectedColor = uiState.titleState.color.toColorOrDefault(),
        )
        val xPosOption=listOf(
            RadioOption(R.string.title_xpos_1),
            RadioOption(R.string.title_xpos_2),
            RadioOption(R.string.title_xpos_3),
        )
        SetRadio(
            title = "タイトルの水平位置",
            options = xPosOption,
            selectedOption = uiState.titleState.xPos,
            onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(xPos = it)) }) },
        )
        val yPosOption=listOf(
            RadioOption(R.string.title_ypos_1),
            RadioOption(R.string.title_ypos_2),
            RadioOption(R.string.title_ypos_3),
        )
        SetRadio(
            title = "タイトルの垂直位置",
            options = yPosOption,
            selectedOption = uiState.titleState.yPos,
            onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(yPos = it)) }) },
        )
        val heightOption=listOf(
            RadioOption(R.string.title_height_1),
            RadioOption(R.string.title_height_2),
            RadioOption(R.string.title_height_3),
        )
        SetRadio(
            title = "タイトルの高さ",
            options = heightOption,
            selectedOption = uiState.titleState.height,
            onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(height = it)) }) },
        )
        val spacingOption=listOf(
            RadioOption(R.string.title_spacing_1),
            RadioOption(R.string.title_spacing_2),
            RadioOption(R.string.title_spacing_3),
        )
        SetRadio(
            title = "タイトルの行間",
            options = spacingOption,
            selectedOption = uiState.titleState.spacing,
            onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(spacing = it)) }) },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TitleSecPreview() {
    EasyPromptTheme {
        TitleSec(viewModel = PromptViewModel(), uiState = PromptUiState())
    }
}