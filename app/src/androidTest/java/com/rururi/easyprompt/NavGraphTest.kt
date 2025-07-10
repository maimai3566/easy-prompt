package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.rururi.easyprompt.ui.navigation.NavGraph
import com.rururi.easyprompt.ui.navigation.Screen
import com.rururi.easyprompt.ui.screen.prompt.PromptType
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavGraphTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var navController: TestNavHostController
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setupNavGraph() {
        composeTestRule.setContent {
            // viewModel = PromptViewModel()
            navController = TestNavHostController(composeTestRule.activity)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(
                promptViewModel = viewModel,
                navController = navController
            )
        }
    }

    @Test
    fun startDestination_isHome() {
        composeTestRule.runOnIdle {
            assertEquals(Screen.Home.route, navController.currentDestination?.route)
        }
    }

    @Test
    fun navigateToPromptScreen_changesDestination() {
        composeTestRule.runOnIdle {
            navController.navigate("prompt/${PromptType.TEXT}")
        }
        composeTestRule.runOnIdle {
            assertEquals(Screen.PromptWizard.route, navController.currentDestination?.route)
            assertEquals(
                PromptType.TEXT.name,
                navController.currentBackStackEntry?.arguments?.getString("type")
            )
        }
    }
}
