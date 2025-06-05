package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.screen.SetTextField
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun PersonSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    LazyColumn(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
            .fillMaxSize()
    ) {
        item {
            SetTextField(
                title = "外見",
                example = "妖精の若い女子、色白、ストレートロングの黒髪に星のティアラ、星のピアス",
                value = uiState.personState.appearance,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(appearance = it)) }) },
                placeholderId = R.string.person_appearance,
            )
            SetTextField(
                title = "表情",
                example = "ぷんぷん怒っている",
                value = uiState.personState.emotion,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(emotion = it)) }) },
                placeholderId = R.string.person_emotion,
            )
            SetTextField(
                title = "ポーズ",
                example = "腕組みをして空中に立って静止",
                value = uiState.personState.pose,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(pose = it)) }) },
                placeholderId = R.string.person_pose,
            )
            SetTextField(
                title = "目線",
                example = "目をつむって上を向いている",
                value = uiState.personState.gaze,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(gaze = it)) }) },
                placeholderId = R.string.person_gaze,
            )
            SetTextField(
                title = "位置",
                example = "中央の上",
                value = uiState.personState.position,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(position = it)) }) },
                placeholderId = R.string.person_position,
            )
            SetTextField(
                title = "スタイル",
                example = "白いTシャツにデニムのショートパンツにスニーカー",
                value = uiState.personState.style,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(style = it)) }) },
                placeholderId = R.string.person_style,
            )
            SetTextField(
                title = "解像度",
                example = "高画質",
                value = uiState.personState.resolution,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(resolution = it)) }) },
                placeholderId = R.string.person_resolution,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Step4Preview(){
    EasyPromptTheme {
        PersonSec(viewModel = PromptViewModel(), uiState = PromptUiState())
    }
}