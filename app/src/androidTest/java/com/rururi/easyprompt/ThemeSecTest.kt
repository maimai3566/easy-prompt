package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.ThemeSec
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ThemeSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            val uiState by vm.uiState.collectAsState()
            ThemeSec(viewModel = viewModel, uiState = uiState)
        }
    }

    //テーマのトーン・ムードのラジオボタンが正しく表示されるか
    @Test
    fun showsThemeToneMoodOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.theme_tone_title)).assertExists()
        composeTestRule.showsText(R.string.theme_tone_1)
        composeTestRule.showsText(R.string.theme_tone_2)
        composeTestRule.showsText(R.string.theme_tone_3)
        composeTestRule.showsText(R.string.theme_tone_4)
        composeTestRule.showsText(R.string.theme_tone_5)
        composeTestRule.showsText(R.string.theme_tone_6)
        composeTestRule.showsText(R.string.theme_tone_7)
        composeTestRule.showsText(R.string.theme_tone_8)
        composeTestRule.showsText(R.string.theme_tone_9)
        composeTestRule.showsText(R.string.theme_tone_10)
        composeTestRule.showsText(R.string.theme_tone_11)
    }
    //キーワードのテキストフィールドが表示されるか
    @Test
    fun showsKeywordTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.theme_keyword_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.theme_keyword_example)).assertExists()
        //placeholder
        composeTestRule.onNode(
            hasText(composeTestRule.activity.getString(R.string.theme_keyword))
        ).assertExists()
    }

    //汎用関数：テキストが表示されるか
    fun AndroidComposeTestRule<*, ComponentActivity>.showsText(res: Int) {
        onNodeWithText(activity.getString(res)).assertExists()
    }

    //汎用関数：ラジオボタンをクリック
    fun AndroidComposeTestRule<*, ComponentActivity>.clickOption(res: Int) {
        onNodeWithText(activity.getString(res)).performClick()
    }
}
