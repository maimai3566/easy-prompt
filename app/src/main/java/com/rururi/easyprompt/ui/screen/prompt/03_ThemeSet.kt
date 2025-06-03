package com.rururi.easyprompt.ui.screen.prompt

import android.R.style.Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.SetTextField
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

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
            title = "テーマのトーン・ムード",
            options = toneOption,
            selectedOption = uiState.themeSetState.tone,
            onOptionSelected = {
                viewModel.updateUiState({ copy(themeSetState = themeSetState.copy(tone = it)) })
            }
        )
        SetTextField(
            title = "キーワード(3,4個程度)",
            example = "梅雨、4コマ漫画、オフィス、コメディ",
            value = uiState.themeSetState.keyword,
            onValueChange = { viewModel.updateUiState({ copy(themeSetState = themeSetState.copy(keyword = it)) }) },
            labelResId = R.string.theme_keyword,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    EasyPromptTheme {
        ThemeSec(
            viewModel = PromptViewModel(),
            uiState = PromptUiState()
        )
    }
}

