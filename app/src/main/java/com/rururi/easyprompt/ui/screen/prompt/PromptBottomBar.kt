package com.rururi.easyprompt.ui.screen.prompt

import android.R.attr.onClick
import android.R.id.message
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.theme.EasyPromptTheme
import com.rururi.easyprompt.utils.log
import kotlinx.coroutines.NonCancellable.isActive

@Composable
fun PromptBottomBar(
    modifier: Modifier = Modifier,
    currentStep: PromptStep,
    onBack: () -> Unit,
    onNext: () -> Unit,
) {
    Column(modifier=modifier) {
        //戻る、次へボタン
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.p_medium)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (currentStep != PromptStep.Canvas) {
                OutlinedButton(         //戻る
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.icon_back),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.weight(0.2f))
            Button(             //次へ
                onClick = onNext,
                modifier = Modifier.weight(1f)
            ){
                Text(text = stringResource(R.string.btn_next),style = MaterialTheme.typography.titleMedium)
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_small)))
        Row(
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PromptStep.entries.forEach { step ->
                val isActive = currentStep.displayName == step.displayName
                Box(
                    modifier = Modifier
                        .size(if(isActive) dimensionResource(R.dimen.dot_large) else dimensionResource(R.dimen.dot_small))
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
        PromptBottomBar(currentStep = PromptStep.Canvas, onBack = {}, onNext = {})
    }
}