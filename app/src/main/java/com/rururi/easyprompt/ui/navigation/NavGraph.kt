package com.rururi.easyprompt.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rururi.easyprompt.ui.screen.HomeScreen
import com.rururi.easyprompt.ui.screen.PresetScreen
import com.rururi.easyprompt.ui.screen.prompt.PromptScreen
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel

@Composable
fun NavGraph(
    promptViewModel: PromptViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ){
        composable(route = Screen.Home.route){
            HomeScreen(
                onNavigateToPrompt = { navController.navigate(Screen.Prompt.route) },
                onNavigateToPreset = { navController.navigate(Screen.Preset.route) }
            )
        }
        composable(route = Screen.Prompt.route) {
            PromptScreen(
                viewModel = promptViewModel,
            )
        }
        composable(route = Screen.Preset.route) {
            PresetScreen()
        }
    }
}