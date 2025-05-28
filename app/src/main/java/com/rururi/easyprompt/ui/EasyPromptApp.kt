package com.rururi.easyprompt.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.ui.navigation.NavGraph
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun EasyPromptApp() {
    Scaffold(
        //topBar
        //bottomBar
    ) { padding ->
        Box(
            modifier = Modifier.padding(padding)
        ){
            NavGraph()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EasyPromptAppPreview() {
    EasyPromptTheme {
        EasyPromptApp()
    }
}