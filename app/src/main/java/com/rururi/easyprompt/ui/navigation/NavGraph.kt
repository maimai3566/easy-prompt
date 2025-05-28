package com.rururi.easyprompt.ui.navigation

import androidx.compose.runtime.Composable
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
fun NavGraph(navController: NavHostController= rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination =Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            HomeScreen(
                onNavigateToPrompt = { navController.navigate(Screen.Prompt.route) },
                onNavigateToPreset = { navController.navigate(Screen.Preset.route) }
            )
        }
        composable(route = Screen.Prompt.route) {
            val viewModel: PromptViewModel = viewModel()
            PromptScreen(
                viewModel = viewModel
            )
        }
        composable(route = Screen.Preset.route) {
            PresetScreen()
        }
    }
}