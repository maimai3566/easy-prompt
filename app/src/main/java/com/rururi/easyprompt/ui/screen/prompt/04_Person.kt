package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.screen.SetRadioText
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
            .imePadding()
    ) {
        item {
            val labelText = stringResource(R.string.option_custom)  //自分で設定
            SetTextField(
                title = stringResource(R.string.person_appearance_title),
//                example = stringResource(R.string.person_appearance_example),
                value = uiState.personState.appearance,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(appearance = it)) }) },
                placeholderId = R.string.person_appearance,
            )
            SetTextField(
                title = stringResource(R.string.person_style_title),
//                example = stringResource(R.string.person_style_example),
                value = uiState.personState.style,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(style = it)) }) },
                placeholderId = R.string.person_style,
            )

            SetTextField(
                title = stringResource(R.string.person_pose_title),
//                example = stringResource(R.string.person_pose_example),
                value = uiState.personState.pose,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(pose = it)) }) },
                placeholderId = R.string.person_pose,
            )
            SetRadioText(
                title = stringResource(R.string.person_emotion_title),
                options = listOf(
                    RadioOption(R.string.person_emotion_1),
                    RadioOption(R.string.person_emotion_2),
                    RadioOption(R.string.person_emotion_3),
                    RadioOption(R.string.person_emotion_4),
                    RadioOption(R.string.person_emotion_5),
                    RadioOption(R.string.option_custom),
                ),
                selectedOption = uiState.personState.optEmotion,
                onOptionSelected = {
                    viewModel.updateUiState {
                        copy(
                            personState = personState.copy(
                                optEmotion = it,
                                emotion = if (it != labelText) it else uiState.personState.emotion,
                            )
                        )
                    }
                },
                value = uiState.personState.emotion,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(emotion = it)) }) },
                placeholderId = R.string.person_emotion,
            )

            SetRadio(
                title = stringResource(R.string.person_gaze_title),
                options = listOf(
                    RadioOption(R.string.person_gaze_1),
                    RadioOption(R.string.person_gaze_2),
                    RadioOption(R.string.person_gaze_3),
                    RadioOption(R.string.person_gaze_4),
                    RadioOption(R.string.person_gaze_5),
                    RadioOption(R.string.person_gaze_6),
                ),
                selectedOption = uiState.personState.gaze,
                onOptionSelected = {viewModel.updateUiState({ copy(personState = personState.copy(gaze = it)) })},
            )
            SetRadio(
                title = stringResource(R.string.person_position_title),
                options = listOf(
                    RadioOption(R.string.person_position_1),
                    RadioOption(R.string.person_position_2),
                    RadioOption(R.string.person_position_3),
                    RadioOption(R.string.person_position_4),
                    RadioOption(R.string.person_position_5),
                ),
                selectedOption = uiState.personState.position,
                onOptionSelected = {viewModel.updateUiState({ copy(personState = personState.copy(position = it)) })},
            )

            SetRadioText(
                title = stringResource(R.string.person_resolution_title),
                options = listOf(
                    RadioOption(R.string.person_resolution_1),
                    RadioOption(R.string.person_resolution_2),
                    RadioOption(R.string.person_resolution_3),
                    RadioOption(R.string.person_resolution_4),
                    RadioOption(R.string.person_resolution_5),
                    RadioOption(R.string.option_custom),
                ),
                selectedOption = uiState.personState.optResolution,
                onOptionSelected = {
                    viewModel.updateUiState {
                        copy(
                            personState = personState.copy(
                                optResolution = it,
                                resolution = if (it != labelText) it else uiState.personState.resolution,
                            )
                        )
                    }
                },
                value = uiState.personState.resolution,
                onValueChange = { viewModel.updateUiState({ copy(personState = personState.copy(resolution = it)) }) },
                placeholderId = R.string.person_resolution,
            )
//            SetRadio(
//                title = stringResource(R.string.person_resolution_title),
//                options = listOf(
//                    RadioOption(R.string.person_resolution_1),
//                    RadioOption(R.string.person_resolution_2),
//                    RadioOption(R.string.person_resolution_3),
//                    RadioOption(R.string.person_resolution_4),
//                    RadioOption(R.string.person_resolution_5),
//                ),
//                selectedOption = uiState.personState.resolution,
//                onOptionSelected = {viewModel.updateUiState({ copy(personState = personState.copy(resolution = it)) })},
//            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun Step4Preview(){
    EasyPromptTheme {
        PersonSec(viewModel = MockPromptViewModel(), uiState = PromptUiState())
    }
}