package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.SetRadio
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

//Camera
@Composable
fun CameraSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
    ) {
        val angleOption = listOf(
            //俯瞰、アイレベル、ローアングル
            RadioOption(R.string.camera_angle_1),
            RadioOption(R.string.camera_angle_2),
            RadioOption(R.string.camera_angle_3,"lowAngle")
        )
        SetRadio(
            title = stringResource(R.string.camera_angle_title),
            options = angleOption,
            selectedOption = uiState.cameraState.angle,
            onOptionSelected = {
                viewModel.updateUiState({ copy(cameraState = cameraState.copy(angle = it)) })
            }
        )

        val distanceOption = listOf(
            //近い、標準、遠い
            RadioOption(R.string.camera_distance_1),
            RadioOption(R.string.camera_distance_2),
            RadioOption(R.string.camera_distance_3)
        )
        SetRadio(
            title = stringResource(R.string.camera_distance_title),
            options = distanceOption,
            selectedOption = uiState.cameraState.distance,
            onOptionSelected = {
                viewModel.updateUiState({ copy(cameraState = cameraState.copy(distance = it)) })
            }
        )

        val frameOption = listOf(
            //クローズアップ、バストアップ、全身
            RadioOption(R.string.camera_framing_1),
            RadioOption(R.string.camera_framing_2),
            RadioOption(R.string.camera_framing_3),
        )
        SetRadio(
            title = stringResource(R.string.camera_framing_title),
            options = frameOption,
            selectedOption = uiState.cameraState.framing,
            onOptionSelected = {
                viewModel.updateUiState({ copy(cameraState = cameraState.copy(framing = it)) })
            }
        )

        val motionOption = listOf(
            //パン、ティルト、ズーム
            RadioOption(R.string.camera_motion_1),
            RadioOption(R.string.camera_motion_2),
            RadioOption(R.string.camera_motion_3),
        )
        SetRadio(
            title = stringResource(R.string.camera_motion_title),
            options = motionOption,
            selectedOption = uiState.cameraState.motion,
            onOptionSelected = {
                viewModel.updateUiState({ copy(cameraState = cameraState.copy(motion = it)) })
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Step2Preview(){
    EasyPromptTheme {
        CameraSec(viewModel = PromptViewModel(), uiState = PromptUiState())
    }
}