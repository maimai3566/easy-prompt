package com.rururi.easyprompt.ui.navigation

import com.rururi.easyprompt.R

sealed class Screen(
    val route: String,
    val title: String,
) {
    object Home : Screen("home", R.string.sc_home.toString())
    object Prompt : Screen("prompt", R.string.sc_prompt.toString())
    object Preset : Screen("preset", R.string.sc_preset.toString())
}