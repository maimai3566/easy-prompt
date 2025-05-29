package com.rururi.easyprompt.ui.screen.prompt

import android.R.attr.onClick
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.theme.EasyPromptTheme
import kotlinx.coroutines.NonCancellable.isActive

@Composable
fun PromptBottomBar(
    modifier: Modifier = Modifier,
    currentStep: PromptStep,
    onSkip: () -> Unit,
    onNext: () -> Unit,
) {
    val totalSteps = PromptStep.all.size
    Column(modifier=modifier) {
        //スキップ、次へボタン
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.p_medium)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(         //スキップ
                onClick = onSkip,
                modifier = Modifier.weight(1f)
            ){
                Text(text = stringResource(R.string.btn_skip))
            }
            Spacer(modifier = Modifier.weight(0.2f))
            Button(             //次へ
                onClick = onNext,
                modifier = Modifier.weight(1f)
            ){
                Text(text = stringResource(R.string.btn_next))
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_small)))
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            PromptStep.all.forEach { step ->
                val isActive = currentStep == step
                Box(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.dot_size))
                        .padding(dimensionResource(R.dimen.p_small))
                        .clip(CircleShape)
                        .background(if(isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PromptBottomBarPreview() {
    EasyPromptTheme {
        PromptBottomBar(currentStep = PromptStep.Canvas, onSkip = {}, onNext = {})
    }
}