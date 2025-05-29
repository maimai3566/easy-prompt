package com.rururi.easyprompt.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rururi.easyprompt.ui.navigation.NavGraph
import com.rururi.easyprompt.ui.screen.prompt.PromptStep
import com.rururi.easyprompt.ui.screen.prompt.PromptTopBar
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.theme.EasyPromptTheme
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rururi.easyprompt.ui.navigation.Screen
import com.rururi.easyprompt.ui.screen.prompt.PromptBottomBar
import com.rururi.easyprompt.utils.log

@Composable
fun EasyPromptApp(promptViewModel: PromptViewModel = viewModel()) {
    val uiState by promptViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState() //直前の画面
    val currentScreen = navBackStackEntry?.destination?.route

    Scaffold(
        //topBar 現在の画面がどこかにより表示を変更
        topBar = {
            when (currentScreen) {
                Screen.Prompt.route -> PromptTopBar(    //プロンプト設定画面
                    currentStep = uiState.currentStep,
                    onBack = { promptViewModel.prevStep() }
                )
                else -> {}
            }
        },
        //bottomBar
        bottomBar = {
            when (currentScreen) {
                Screen.Prompt.route -> PromptBottomBar(    //プロンプト設定画面
                    currentStep = uiState.currentStep,
                    onSkip = { promptViewModel.nextStep() },
                    onNext = { promptViewModel.nextStep() },
                    modifier = Modifier.navigationBarsPadding()
                )
                else -> {}
            }
        }
    ) { padding ->
        NavGraph(
            navController = navController,
            promptViewModel = promptViewModel,
            modifier = Modifier.padding(padding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EasyPromptAppPreview() {
    EasyPromptTheme {
        EasyPromptApp()
    }
}