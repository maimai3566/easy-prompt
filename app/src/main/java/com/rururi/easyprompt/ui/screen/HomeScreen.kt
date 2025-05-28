package com.rururi.easyprompt.ui.screen

import android.R.attr.onClick
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToPrompt: () -> Unit = {},
    onNavigateToPreset: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier
            .fillMaxSize()
    ) {
        Button(onClick =onNavigateToPrompt){
            Text(
                text = stringResource(R.string.btn_prompt),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        Button(onClick = onNavigateToPreset){
            Text(
                text = stringResource(R.string.btn_preset),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EasyPromptTheme {
        HomeScreen()
    }
}