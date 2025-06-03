package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.RadioSelector
import com.rururi.easyprompt.ui.screen.SetColor
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.theme.EasyPromptTheme
import com.rururi.easyprompt.ext.toColorOrDefault
import com.rururi.easyprompt.ext.toRgbaString

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
            RadioOption(R.string.light_style_1),
            RadioOption(R.string.light_style_2),
            RadioOption(R.string.light_style_3),
            RadioOption(R.string.light_style_4),
            RadioOption(R.string.light_style_5),
        )
        SetRadio(
            title = "ライティングスタイル",
            options = styleOption,
            selectedOption = uiState.lightState.style,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(style = it)) }) },
        )

        val directionOption = listOf(
            RadioOption(R.string.light_direction_1),
            RadioOption(R.string.light_direction_2),
            RadioOption(R.string.light_direction_3),
            RadioOption(R.string.light_direction_4),
        )
        SetRadio(
            title = "光の方向",
            options = directionOption,
            selectedOption = uiState.lightState.direction,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(direction = it)) }) },
        )

        SetColor(
            title = "光の色",
            onColorSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(color = it.toRgbaString())) }) },
            selectedColor = uiState.lightState.color.toColorOrDefault(),
        )

        Text(text = "■光の強度",style = MaterialTheme.typography.titleLarge)
        val intensityOption = listOf(
            RadioOption(R.string.light_intensity_1),
            RadioOption(R.string.light_intensity_2),
            RadioOption(R.string.light_intensity_3),
        )
        RadioSelector(
            options = intensityOption,
            selectedOption = uiState.lightState.intensity,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(intensity = it)) }) },
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_medium)))

        Text(text = "■光のコントラスト",style = MaterialTheme.typography.titleLarge)
        val contrastOption = listOf(
            RadioOption(R.string.light_contrast_1),
            RadioOption(R.string.light_contrast_2),
            RadioOption(R.string.light_contrast_3),
        )
        RadioSelector(
            options = contrastOption,
            selectedOption = uiState.lightState.contrast,
            onOptionSelected = { viewModel.updateUiState({ copy(lightState = lightState.copy(contrast = it)) }) },
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_medium)))
    }
}

@Preview(showBackground = true)
@Composable
fun Step3Preview(){
    EasyPromptTheme {
        LightingSec(viewModel = PromptViewModel(), uiState = PromptUiState())
    }
}