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
import com.rururi.easyprompt.ui.screen.prompt.CameraSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.utils.log
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CameraSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            val uiState by vm.uiState.collectAsState()
            CameraSec(viewModel = vm, uiState =uiState)
        }
    }

    //カメラの角度のラジオボタンが正しく表示されるか
    @Test
    fun showsCameraAngleOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.camera_angle_title)).assertExists()
        composeTestRule.showsText(R.string.camera_angle_1)
        composeTestRule.showsText(R.string.camera_angle_2)
        composeTestRule.showsText(R.string.camera_angle_3)
    }

    //カメラの距離のラジオボタンが正しく表示されるか
    @Test
    fun showsCameraDistanceOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.camera_distance_title)).assertExists()
        composeTestRule.showsText(R.string.camera_distance_1)
        composeTestRule.showsText(R.string.camera_distance_2)
        composeTestRule.showsText(R.string.camera_distance_3)
    }

    //カメラのフレームのラジオボタンが正しく表示されるか
    @Test
    fun showsCameraFramingOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.camera_framing_title)).assertExists()
        composeTestRule.showsText(R.string.camera_framing_1)
        composeTestRule.showsText(R.string.camera_framing_2)
        composeTestRule.showsText(R.string.camera_framing_3)
    }

    //カメラの運動のラジオボタンが正しく表示されるか
    @Test
    fun showsCameraMotionOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.camera_motion_title)).assertExists()
        composeTestRule.showsText(R.string.camera_motion_1)
        composeTestRule.showsText(R.string.camera_motion_2)
        composeTestRule.showsText(R.string.camera_motion_3)
    }

    //カメラの角度ラジオボタンをクリックしたときに、選択したものにチェックが入っているか
    @Test
    fun updatesCameraAngleWhenOptionSelected() {
        val tag = "lowAngle"
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).performClick()     //lowAngleというタグをクリックしてる
        composeTestRule.waitForIdle()   //待つ

        composeTestRule.runOnIdle {
            val selected = viewModel.uiState.value.cameraState.angle     //uiStateの値を取得
            log("selected: $selected")
            assert(selected == composeTestRule.activity.getString(R.string.camera_angle_3))  //uiStateの値がローアングルになったことをアサート
        }
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).assertIsSelected()    //lowAngleというタグが選択されていることをアサート
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
