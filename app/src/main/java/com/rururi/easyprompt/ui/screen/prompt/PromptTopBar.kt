package com.rururi.easyprompt.ui.screen.prompt


import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.rururi.easyprompt.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptTopBar(
    currentStep: PromptStep,
    onHome: () -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(
                text = "★ ${currentStep.displayName}",
                style = MaterialTheme.typography.displaySmall
            )
        },
        navigationIcon = {
            IconButton(onClick = onHome) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = stringResource(R.string.sc_home),
                    tint = MaterialTheme.colorScheme.primaryContainer,
                    modifier = Modifier.size(dimensionResource(R.dimen.icon_large))
                )
            }
        },
        modifier = modifier
    )
}

/*
@Preview(showBackground = true)
@Composable
fun PromptTopBarPreview() {
    EasyPromptTheme {
        PromptTopBar(
            currentStep = PromptStep.Canvas,
            onHome = {}
        )
    }
}
*/