package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rururi.easyprompt.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PromptTopBar(
    currentStep: PromptStep,
    onBack:() -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(
                text = "Step${currentStep.number} ${currentStep.displayName}",
                style = MaterialTheme.typography.displaySmall
            )
        },
//        navigationIcon = {
//            IconButton(onClick = onBack) {
//                Icon(
//                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                    contentDescription = stringResource(R.string.icon_back)
//                )
//            }
//        },
        modifier = modifier
    )
}