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
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rururi.easyprompt.ui.screen.prompt.PersonSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.utils.log
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PersonSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            val uiState by vm.uiState.collectAsState()
            PersonSec(viewModel = vm,uiState = uiState)
        }
    }

    //外見のテキストフィールドが表示されるか
    @Test
    fun showsAppearanceTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_appearance_title)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_appearance))).assertExists()  //placeholder
    }

    //ポーズのテキストフィールドが表示されるか
    @Test
    fun showsPoseTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_pose_title)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_pose))).assertExists()
    }

    //スタイルのテキストフィールドが表示されるか
    @Test
    fun showsStyleTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_style_title)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_style))).assertExists()
    }

    //表情のラジオボタンが表示されるか
    @Test
    fun showsEmotionOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_emotion_title)).assertExists()
        composeTestRule.showsText(R.string.person_emotion_1)
        composeTestRule.showsText(R.string.person_emotion_2)
        composeTestRule.showsText(R.string.person_emotion_3)
        composeTestRule.showsText(R.string.person_emotion_4)
        composeTestRule.showsText(R.string.person_emotion_5)
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.option_custom)).assertCountEquals(2)
    }

    //目線のラジオボタンが表示されるか
    @Test
    fun showsGazeOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_gaze_title)).assertExists()
        composeTestRule.showsText(R.string.person_gaze_1)
        composeTestRule.showsText(R.string.person_gaze_2)
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.person_gaze_3)).assertCountEquals(2)
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.person_gaze_4)).assertCountEquals(2)
        composeTestRule.showsText(R.string.person_gaze_5)
        composeTestRule.showsText(R.string.person_gaze_6)
    }

    //位置のラジオボタンが表示されるか
    @Test
    fun showsPositionOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_position_title)).assertExists()
        composeTestRule.showsText(R.string.person_position_1)
        composeTestRule.showsText(R.string.person_position_2)
        composeTestRule.showsText(R.string.person_position_3)
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.person_position_4)).assertCountEquals(2)
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.person_position_5)).assertCountEquals(2)
    }

    //解像度のラジオボタンが表示されるか
    @Test
    fun showsResolutionOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_resolution_title)).assertExists()
        composeTestRule.showsText(R.string.person_resolution_1)
        composeTestRule.showsText(R.string.person_resolution_2)
        composeTestRule.showsText(R.string.person_resolution_3)
        composeTestRule.showsText(R.string.person_resolution_4)
        composeTestRule.showsText(R.string.person_resolution_5)
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.option_custom)).assertCountEquals(2)
    }

    //位置ラジオボタンをクリックしたときに、選択したものにチェックが入っているか
    @Test
    fun updatesPersonResolutionWhenOptionSelected() {
        val tag = "pos5"
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).performClick()     //下をクリック
        composeTestRule.waitForIdle()   //待つ

        composeTestRule.runOnIdle {
            val selected = viewModel.uiState.value.personState.position    //uiStateの値を取得
            log("selected: $selected")
            assert(selected == composeTestRule.activity.getString(R.string.person_position_5))  //uiStateの値が下になったことをアサート
        }
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).assertIsSelected()    //pos5というタグが選択されていることをアサート
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
