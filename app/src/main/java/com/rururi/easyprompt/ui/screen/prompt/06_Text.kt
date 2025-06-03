package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.rururi.easyprompt.ui.screen.RadioOption
import com.rururi.easyprompt.ui.screen.RadioSelector
import com.rururi.easyprompt.R

@Composable
fun TextSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState,
) {
    Column(
        modifier = modifier
            .padding(dimensionResource(R.dimen.p_small))
    ) {
        Text(
            text = "■文字",
            style = MaterialTheme.typography.titleLarge
        )
        val sizeOption = listOf(
            RadioOption(R.string.canvas_size_1),
            RadioOption(R.string.canvas_size_2),
            RadioOption(R.string.canvas_size_3)
        )
        RadioSelector(
            options = sizeOption,
            selectedOption = uiState.canvasState.size,
            onOptionSelected = {  },
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_small)))
    }
}