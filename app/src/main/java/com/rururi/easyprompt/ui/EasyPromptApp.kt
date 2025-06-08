package com.rururi.easyprompt.ui

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rururi.easyprompt.ui.navigation.NavGraph
import com.rururi.easyprompt.ui.navigation.Screen
import com.rururi.easyprompt.ui.screen.prompt.PromptBottomBar
import com.rururi.easyprompt.ui.screen.prompt.PromptTopBar
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.theme.EasyPromptTheme
import kotlinx.coroutines.delay

@Composable
fun EasyPromptApp(promptViewModel: PromptViewModel = viewModel()) {
    val uiState by promptViewModel.uiState.collectAsState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState() //直前の画面
    val currentScreen = navBackStackEntry?.destination?.route
    val clipboard = LocalClipboardManager.current
    var copied by remember { mutableStateOf(false) }

    //コピーしたら、2秒後に未コピーに戻す
    LaunchedEffect(copied) {
        if (copied) {
            delay(2000L)
            copied = false
        }
    }
    Scaffold(
        //topBar 現在の画面がどこかにより表示を変更
        topBar = {
            if (currentScreen?.startsWith("prompt") == true) {
                PromptTopBar(
                    currentStep = uiState.currentStep,
                    onHome = {
                        promptViewModel.reset() //状態をリセット
                        navController.navigate(Screen.Home.route) { //ホーム画面へ
                            popUpTo(Screen.Home.route) { inclusive = true } //backstackも削除
                        }
                    },
                )
            }
        },
        //bottomBar
        bottomBar = {
            if (currentScreen?.startsWith("prompt") == true) {
                PromptBottomBar(    //プロンプト設定画面
                    currentStep = uiState.currentStep,
                    promptType = uiState.promptType,
                    onBack = { promptViewModel.prevStep() },
                    onNext = { promptViewModel.nextStep() },
                    onCopy = {
                        clipboard.setText(AnnotatedString(uiState.result))
                        copied = true
                    },
                    copied = copied,
                    modifier = Modifier.navigationBarsPadding()
                )
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