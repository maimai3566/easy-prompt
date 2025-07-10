package com.rururi.easyprompt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.rururi.easyprompt.data.UserPreferencesRepository
import com.rururi.easyprompt.ui.EasyPromptApp
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.theme.EasyPromptTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var userPreferencesRepository: UserPreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val promptViewModel: PromptViewModel = hiltViewModel()
            EasyPromptTheme {
                EasyPromptApp(
                    userPreferencesRepository = userPreferencesRepository,
                    promptViewModel = promptViewModel
                )
            }
        }
    }
}
