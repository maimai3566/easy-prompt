package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rururi.easyprompt.ui.screen.prompt.LightingSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LightingSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            val uiState by vm.uiState.collectAsState()
            LightingSec(viewModel =vm, uiState = uiState)
        }
    }

    //ライティングスタイルの選択肢が表示されるか
    @Test
    fun showsLightingStyleOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.light_style_title)).assertExists()
        composeTestRule.showsTag("light_style_1")
        composeTestRule.showsTag("light_style_2")
        composeTestRule.showsTag("light_style_3")
        composeTestRule.showsTag("light_style_4")
        composeTestRule.showsTag("light_style_5")
    }

    //光の方向の選択肢が表示されるか
    @Test
    fun showsLightingDirectionOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.light_direction_title)).assertExists()
        composeTestRule.showsTag("light_direction_1")
        composeTestRule.showsTag("light_direction_2")
        composeTestRule.showsTag("light_direction_3")
        composeTestRule.showsTag("light_direction_4")
    }

    //光の色が設定されるか
    @Test
    fun updatesLightingColor() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.light_color_title)).assertExists()
        composeTestRule.showsText(R.string.select_color)
    }

    //光の強度の選択肢が表示されるか
    @Test
    fun showsLightingIntensityOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.light_intensity_title)).assertExists()
        composeTestRule.showsTag("light_intensity_1")
        composeTestRule.showsTag("light_intensity_2")
        composeTestRule.showsTag("light_intensity_3")
    }

    //光のコントラストの選択肢が表示されるか
    @Test
    fun showsLightingContrastOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.light_contrast_title)).assertExists()
        composeTestRule.showsTag("light_contrast_1")
        composeTestRule.showsTag("light_contrast_2")
        composeTestRule.showsTag("light_contrast_3")
    }

    //ライティングスタイルのラジオボタンをクリックしたときに、選択したものにチェックが入っているか
    @Test
    fun updatesLightingStyleWhenOptionSelected() {
        val tag = "light_style_5"
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).performClick()     //blueTimeというタグをクリック
        composeTestRule.waitForIdle()   //待つ
        composeTestRule.runOnIdle {
            val selected = viewModel.uiState.value.lightState.style
            assert(selected == composeTestRule.activity.getString(R.string.light_style_5))
        }
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).assertIsSelected()    //lowAngleというタグが選択されていることをアサート
    }

    //汎用関数：テキストが表示されるか
    fun AndroidComposeTestRule<*, ComponentActivity>.showsText(res: Int) {
        onNodeWithText(activity.getString(res)).assertExists()
    }

    //汎用関数：タグが存在するか
    fun AndroidComposeTestRule<*, ComponentActivity>.showsTag(tag: String) {
        onNodeWithTag(tag,useUnmergedTree = true).assertExists()
    }

    //汎用関数：ラジオボタンをクリック
    fun AndroidComposeTestRule<*, ComponentActivity>.clickOption(res: Int) {
        onNodeWithText(activity.getString(res)).performClick()
    }
}
