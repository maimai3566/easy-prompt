package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.TitleSec
import com.rururi.easyprompt.utils.log
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TitleSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            val uiState by vm.uiState.collectAsState()
            TitleSec(viewModel = vm, uiState = uiState)
        }
    }

    //タイトルのテキストフィールドが表示されるか
    @Test
    fun showsTextFields() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_text_title)).assertExists()
//        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.title_text_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.title_text))).assertExists()
    }

    //タイトルのフォントのラジオボタンが表示されるか
    @Test
    fun showsFamilyOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_family_title)).assertExists()
        composeTestRule.showsTag("title_family_1")
        composeTestRule.showsTag("title_family_2")
        composeTestRule.showsTag("title_family_3")
        composeTestRule.showsTag("title_family_4")
        composeTestRule.showsTag("title_family_5")
        composeTestRule.showsTag("option_custom")
    }


    //タイトルのサイズのラジオボタンが表示されるか
    @Test
    fun showsSizeOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_size_title)).assertExists()
        composeTestRule.showsTag("title_size_1")
        composeTestRule.showsTag("title_size_2")
        composeTestRule.showsTag("title_size_3")
    }
    //タイトルの太さのラジオボタンが表示されるか
    @Test
    fun showsWeightOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_weight_title)).assertExists()
        composeTestRule.showsTag("title_weight_1")
        composeTestRule.showsTag("title_weight_2")
    }
    //タイトルの色が表示されるか
    @Test
    fun showsColorOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_color_title)).assertExists()
        composeTestRule.showsText(R.string.select_color)
    }

    //タイトルの水平位置のラジオボタンが表示されるか
    @Test
    fun showsXposOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_xpos_title)).assertExists()
        composeTestRule.showsTag("title_xpos_1")
        composeTestRule.showsTag("title_xpos_2")
        composeTestRule.showsTag("title_xpos_3")
    }
    //タイトルの垂直位置のラジオボタンが表示されるか
    @Test
    fun showsYposOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_ypos_title)).assertExists()
        composeTestRule.showsTag("title_ypos_1")
        composeTestRule.showsTag("title_ypos_2")
        composeTestRule.showsTag("title_ypos_3")
    }
    //タイトルの高さのラジオボタンが表示されるか
    @Test
    fun showsHeightOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_height_title))
            .assertExists()
        composeTestRule.showsTag("title_height_1")
        composeTestRule.showsTag("title_height_2")
        composeTestRule.showsTag("title_height_3")
    }
    //タイトルの行間のラジオボタンが表示されるか
    @Test
    fun showsSpacingOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.title_spacing_title))
            .assertExists()
        composeTestRule.showsTag("title_spacing_1")
        composeTestRule.showsTag("title_spacing_2")
        composeTestRule.showsTag("title_spacing_3")
    }

    //自分で設定を選択したとき、例とテキストフィールドが表示されるか
    @Test
    fun showsCustomTextfieldWhenCustomSelected() {
        composeTestRule.onNodeWithTag("option_custom",useUnmergedTree = true).performClick()
        composeTestRule.onAllNodesWithTag("commonTextField").assertCountEquals(2)
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.title_family_example)).assertExists()
    }

    //タイトルの水平位置をクリックしたとき、選択したものにチェックが入っているか
    @Test
    fun updatesXposWhenOptionSelected() {
        val tag = "title_xpos_3"
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).performClick()     //右に配置
        composeTestRule.waitForIdle()   //待つ

        composeTestRule.runOnIdle {
            val selected = viewModel.uiState.value.titleState.xPos     //uiStateの値を取得
            log("selected: $selected")
            assert(selected == composeTestRule.activity.getString(R.string.title_xpos_3))   //uiStateの値が右に配置になったことをアサート
        }
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).assertIsSelected()    //右に配置にチェックが入っていることをアサート
    }

    //汎用関数：LazyColumnで対象のタグまでスクロールする関数
    fun AndroidComposeTestRule<*, ComponentActivity>.scrollUntilTag(tag: String) {
        onNodeWithTag(tag,useUnmergedTree = true).performScrollTo()
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
