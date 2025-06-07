package com.rururi.easyprompt.ui.navigation

sealed class Screen(
    val route: String,
) {
    object Home : Screen("home")
    object PromptWizard : Screen("prompt/{type}")
}