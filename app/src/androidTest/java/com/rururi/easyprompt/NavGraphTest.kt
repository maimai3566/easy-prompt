package com.rururi.easyprompt

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.rururi.easyprompt.data.UserPreferencesRepository
import com.rururi.easyprompt.ui.navigation.NavGraph
import com.rururi.easyprompt.ui.navigation.Screen
import com.rururi.easyprompt.ui.screen.prompt.PromptType
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class NavGraphTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var userPreferencesRepository: UserPreferencesRepository

    private lateinit var navController: TestNavHostController

    @Before
    fun setupNavGraph() {
        hiltRule.inject()
        composeTestRule.setContent {
            navController = TestNavHostController(composeTestRule.activity)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(
                navController = navController,
                userPreferencesRepository = userPreferencesRepository,
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
