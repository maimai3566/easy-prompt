package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.SetTextField

@Composable
fun ThemeSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
    ) {
        val toneOption = listOf(
            RadioOption(R.string.theme_tone_1),
            RadioOption(R.string.theme_tone_2),
            RadioOption(R.string.theme_tone_3),
            RadioOption(R.string.theme_tone_4),
            RadioOption(R.string.theme_tone_5),
            RadioOption(R.string.theme_tone_6),
            RadioOption(R.string.theme_tone_7),
            RadioOption(R.string.theme_tone_8),
            RadioOption(R.string.theme_tone_9),
            RadioOption(R.string.theme_tone_10),
            RadioOption(R.string.theme_tone_11),
        )
        SetRadio(
            title = stringResource(R.string.theme_tone_title),
            options = toneOption,
            selectedOption = uiState.themeSetState.tone,
            onOptionSelected = {
                viewModel.updateUiState({ copy(themeSetState = themeSetState.copy(tone = it)) })
            }
        )
        SetTextField(
            title = stringResource(R.string.theme_keyword_title),
//            example = stringResource(R.string.theme_keyword_example),

            value = uiState.themeSetState.keyword,
            onValueChange = { viewModel.updateUiState({ copy(themeSetState = themeSetState.copy(keyword = it)) }) },
            placeholderId = R.string.theme_keyword,
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun Step3Preview() {
    EasyPromptTheme {
        ThemeSetSec(
            viewModel = MockPromptViewModel(),
            uiState = PromptUiState()
        )
    }
}
*/