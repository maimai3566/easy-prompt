package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ext.toColorOrDefault
import com.rururi.easyprompt.ext.toRgbaString
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetColor
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.prompt.MockPromptViewModel
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun LightingSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
    ) {
        val styleOption = listOf(
            RadioOption(R.string.light_style_1,"light_style_1"),
            RadioOption(R.string.light_style_2,"light_style_2"),
            RadioOption(R.string.light_style_3,"light_style_3"),
            RadioOption(R.string.light_style_4,"light_style_4"),
            RadioOption(R.string.light_style_5,"light_style_5"),
        )
        SetRadio(
            title = stringResource(R.string.light_style_title),
            options = styleOption,
            selectedOption = uiState.lightState.style,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(style = it)) }) },
        )

        val directionOption = listOf(
            RadioOption(R.string.light_direction_1,"light_direction_1"),
            RadioOption(R.string.light_direction_2,"light_direction_2"),
            RadioOption(R.string.light_direction_3,"light_direction_3"),
            RadioOption(R.string.light_direction_4,"light_direction_4"),
        )
        SetRadio(
            title = stringResource(R.string.light_direction_title),
            options = directionOption,
            selectedOption = uiState.lightState.direction,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(direction = it)) }) },
        )

        SetColor(
            title = stringResource(R.string.light_color_title),
            onColorSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(color = it.toRgbaString())) }) },
            selectedColor = uiState.lightState.color.toColorOrDefault(),
        )

        val intensityOption = listOf(
            RadioOption(R.string.light_intensity_1,"light_intensity_1"),
            RadioOption(R.string.light_intensity_2,"light_intensity_2"),
            RadioOption(R.string.light_intensity_3,"light_intensity_3"),
        )
        SetRadio(
            title = stringResource(R.string.light_intensity_title),
            options = intensityOption,
            selectedOption = uiState.lightState.intensity,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(intensity = it)) }) },
        )

        val contrastOption = listOf(
            RadioOption(R.string.light_contrast_1,"light_contrast_1"),
            RadioOption(R.string.light_contrast_2,"light_contrast_2"),
            RadioOption(R.string.light_contrast_3,"light_contrast_3"),
        )
        SetRadio(
            title = stringResource(R.string.light_contrast_title),
            options = contrastOption,
            selectedOption = uiState.lightState.contrast,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(contrast = it)) }) },
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun LightingSecPreview(){
    EasyPromptTheme {
        LightingSec(viewModel = MockPromptViewModel(), uiState = PromptUiState())
    }
}
*/