package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.rururi.easyprompt.ui.screen.SetTextField
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun TitleSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
            .fillMaxSize()
            .imePadding()
            .testTag("titleLazyColumn")
    ) {

        item {
            SetTextField(
                title = stringResource(R.string.title_text_title),
//                example = "",
                value = uiState.titleState.text,
                onValueChange = { viewModel.updateUiState({ copy(titleState = titleState.copy(text = it)) }) },
                placeholderId = R.string.title_text,
            )

            val familyOption= listOf(
                RadioOption(R.string.title_family_1,"title_family_1"),
                RadioOption(R.string.title_family_2,"title_family_2"),
                RadioOption(R.string.title_family_3,"title_family_3"),
                RadioOption(R.string.title_family_4,"title_family_4"),
                RadioOption(R.string.title_family_5,"title_family_5"),
                RadioOption(R.string.option_custom,"option_custom"),
            )
            val labelCustom = stringResource(R.string.option_custom)
            SetRadioText(
                title = stringResource(R.string.title_family_title),
                options = familyOption,
                selectedOption = uiState.titleState.optFamily,
                onOptionSelected = {
                    viewModel.updateUiState {
                        copy(
                            titleState = titleState.copy(
                                optFamily = it,    //ラジオボタンで選択した内容
                                family = if (it != labelCustom) it else uiState.titleState.family,  //ラジオボタンで選択した内容or入力した内容
                            )
                        )
                    }
                },
//                example = stringResource(R.string.title_family_example),
                value = uiState.titleState.family,
                onValueChange = { viewModel.updateUiState({ copy(titleState = titleState.copy(family = it)) }) },
                placeholderId = R.string.title_placeholder,
            )

            val sizeOption=listOf(
                RadioOption(R.string.title_size_1,"title_size_1"),
                RadioOption(R.string.title_size_2,"title_size_2"),
                RadioOption(R.string.title_size_3,"title_size_3"),
            )
            SetRadio(
                title = stringResource(R.string.title_size_title),
                options = sizeOption,
                selectedOption = uiState.titleState.size,
                onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(size = it)) }) },
            )

            val weightOption=listOf(
                RadioOption(R.string.title_weight_1,"title_weight_1"),
                RadioOption(R.string.title_weight_2,"title_weight_2"),
            )
            SetRadio(
                title = stringResource(R.string.title_weight_title),
                options = weightOption,
                selectedOption = uiState.titleState.weight,
                onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(weight = it)) }) }
            )

            SetColor(
                title = stringResource(R.string.title_color_title),
                onColorSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(color = it.toRgbaString())) }) },
                selectedColor = uiState.titleState.color.toColorOrDefault(),
            )

            val xPosOption=listOf(
                RadioOption(R.string.title_xpos_1,"title_xpos_1"),
                RadioOption(R.string.title_xpos_2,"title_xpos_2"),
                RadioOption(R.string.title_xpos_3,"title_xpos_3"),
            )
            SetRadio(
                title = stringResource(R.string.title_xpos_title),
                options = xPosOption,
                selectedOption = uiState.titleState.xPos,
                onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(xPos = it)) }) },
            )

            val yPosOption=listOf(
                RadioOption(R.string.title_ypos_1,"title_ypos_1"),
                RadioOption(R.string.title_ypos_2,"title_ypos_2"),
                RadioOption(R.string.title_ypos_3,"title_ypos_3"),
            )
            SetRadio(
                title = stringResource(R.string.title_ypos_title),
                options = yPosOption,
                selectedOption = uiState.titleState.yPos,
                onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(yPos = it)) }) },
            )

            val heightOption=listOf(
                RadioOption(R.string.title_height_1,"title_height_1"),
                RadioOption(R.string.title_height_2,"title_height_2"),
                RadioOption(R.string.title_height_3,"title_height_3"),
            )
            SetRadio(
                title = stringResource(R.string.title_height_title),
                options = heightOption,
                selectedOption = uiState.titleState.height,
                onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(height = it)) }) },
            )

            val spacingOption=listOf(
                RadioOption(R.string.title_spacing_1,"title_spacing_1"),
                RadioOption(R.string.title_spacing_2,"title_spacing_2"),
                RadioOption(R.string.title_spacing_3,"title_spacing_3"),
            )
            SetRadio(
                title = stringResource(R.string.title_spacing_title),
                options = spacingOption,
                selectedOption = uiState.titleState.spacing,
                onOptionSelected = { viewModel.updateUiState({ copy(titleState = titleState.copy(spacing = it)) }) },
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TitleSecPreview() {
    EasyPromptTheme {
        TitleSec(viewModel = MockPromptViewModel(), uiState = PromptUiState())
    }
}
