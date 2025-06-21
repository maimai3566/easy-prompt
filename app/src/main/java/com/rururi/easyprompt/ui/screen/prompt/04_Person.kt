package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
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
                title = stringResource(R.string.person_appearance_title),
                example = stringResource(R.string.person_appearance_example),
                value = uiState.personState.appearance,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(appearance = it)) }) },
                placeholderId = R.string.person_appearance,
            )
            SetTextField(
                title = stringResource(R.string.person_emotion_title),
                example = stringResource(R.string.person_emotion_example),
                value = uiState.personState.emotion,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(emotion = it)) }) },
                placeholderId = R.string.person_emotion,
            )
            SetTextField(
                title = stringResource(R.string.person_pose_title),
                example = stringResource(R.string.person_pose_example),
                value = uiState.personState.pose,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(pose = it)) }) },
                placeholderId = R.string.person_pose,
            )
            SetTextField(
                title = stringResource(R.string.person_gaze_title),
                example = stringResource(R.string.person_gaze_example),
                value = uiState.personState.gaze,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(gaze = it)) }) },
                placeholderId = R.string.person_gaze,
            )
            SetTextField(
                title = stringResource(R.string.person_position_title),
                example = stringResource(R.string.person_position_example),
                value = uiState.personState.position,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(position = it)) }) },
                placeholderId = R.string.person_position,
            )
            SetTextField(
                title = stringResource(R.string.person_style_title),
                example = stringResource(R.string.person_style_example),
                value = uiState.personState.style,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(style = it)) }) },
                placeholderId = R.string.person_style,
            )
            SetTextField(
                title = stringResource(R.string.person_resolution_title),
                example = stringResource(R.string.person_resolution_example),
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