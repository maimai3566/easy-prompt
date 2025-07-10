package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ext.toColorOrDefault
import com.rururi.easyprompt.ext.toRgbaString
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetColor
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.SetRadioText
import com.rururi.easyprompt.ui.screen.SetTextField

@Composable
fun BodySec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
            .fillMaxSize()
            .imePadding()
    ) {
        item {
            SetTextField(
                title = stringResource(R.string.body_text_title),
//                example = "",
                value = uiState.bodyState.text,
                onValueChange = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(text = it)) }) },
                placeholderId = R.string.body_text,
                singleLine = false,
                maxLines = 5,
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
                title = stringResource(R.string.body_family_title),
                options = familyOption,
                selectedOption = uiState.bodyState.optFamily,
                onOptionSelected = {
                    viewModel.updateUiState{
                        copy(
                            bodyState = bodyState.copy(
                                optFamily = it,
                                family = if (it != labelCustom) it else uiState.bodyState.family,
                            )
                        )
                    }
                },
//                example = stringResource(R.string.title_family_example),
                value = uiState.bodyState.family,
                onValueChange = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(family = it)) }) },
                placeholderId = R.string.title_placeholder,
            )
            val sizeOption=listOf(
                RadioOption(R.string.title_size_1,"title_size_1"),
                RadioOption(R.string.title_size_2,"title_size_2"),
                RadioOption(R.string.title_size_3,"title_size_3"),
            )
            SetRadio(
                title = stringResource(R.string.body_size_title),
                options = sizeOption,
                selectedOption = uiState.bodyState.size,
                onOptionSelected = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(size = it)) }) },
            )

            val weightOption=listOf(
                RadioOption(R.string.title_weight_1,"title_weight_1"),
                RadioOption(R.string.title_weight_2,"title_weight_2"),
            )
            SetRadio(
                title = stringResource(R.string.body_weight_title),
                options = weightOption,
                selectedOption = uiState.bodyState.weight,
                onOptionSelected = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(weight = it)) }) }
            )

            SetColor(
                title = stringResource(R.string.body_color_title),
                onColorSelected = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(color = it.toRgbaString())) }) },
                selectedColor = uiState.bodyState.color.toColorOrDefault(),
            )
            val xPosOption=listOf(
                RadioOption(R.string.title_xpos_1,"title_xpos_1"),
                RadioOption(R.string.title_xpos_2,"title_xpos_2"),
                RadioOption(R.string.title_xpos_3,"title_xpos_3"),
            )
            SetRadio(
                title = stringResource(R.string.body_xpos_title),
                options = xPosOption,
                selectedOption = uiState.bodyState.xPos,
                onOptionSelected = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(xPos = it)) }) },
            )

            val yPosOption=listOf(
                RadioOption(R.string.title_ypos_1,"title_ypos_1"),
                RadioOption(R.string.title_ypos_2,"title_ypos_2"),
                RadioOption(R.string.title_ypos_3,"title_ypos_3"),
            )
            SetRadio(
                title = stringResource(R.string.body_ypos_title),
                options = yPosOption,
                selectedOption = uiState.bodyState.yPos,
                onOptionSelected = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(yPos = it)) }) },
            )

            val heightOption=listOf(
                RadioOption(R.string.title_height_1,"title_height_1"),
                RadioOption(R.string.title_height_2,"title_height_2"),
                RadioOption(R.string.title_height_3,"title_height_3"),
            )
            SetRadio(
                title = stringResource(R.string.body_height_title),
                options = heightOption,
                selectedOption = uiState.bodyState.height,
                onOptionSelected = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(height = it)) }) },
            )

            val spacingOption=listOf(
                RadioOption(R.string.title_spacing_1,"title_spacing_1"),
                RadioOption(R.string.title_spacing_2,"title_spacing_2"),
                RadioOption(R.string.title_spacing_3,"title_spacing_3"),
            )
            SetRadio(
                title = stringResource(R.string.body_spacing_title),
                options = spacingOption,
                selectedOption = uiState.bodyState.spacing,
                onOptionSelected = { viewModel.updateUiState({ copy(bodyState = bodyState.copy(spacing = it)) }) },
            )
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun BodySecPreview() {
    EasyPromptTheme {
        BodySec(viewModel = MockPromptViewModel(), uiState = PromptUiState())
    }
}
*/