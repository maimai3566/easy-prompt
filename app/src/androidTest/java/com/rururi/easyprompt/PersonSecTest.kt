package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rururi.easyprompt.ui.screen.prompt.PersonSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
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
            PersonSec(viewModel = vm, uiState = uiState)
        }
    }

    //外見のテキストフィールドが表示されるか
    @Test
    fun showsAppearanceTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_appearance_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.person_appearance_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_appearance))).assertExists()  //placeholder
    }

    //表情のテキストフィールドが表示されるか
    @Test
    fun showsEmotionTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_emotion_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.person_emotion_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_emotion))).assertExists()
    }

    //ポーズのテキストフィールドが表示されるか
    @Test
    fun showsPoseTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_pose_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.person_pose_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_pose))).assertExists()
    }

    //目線のテキストフィールドが表示されるか
    @Test
    fun showsGazeTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_gaze_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.person_gaze_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_gaze))).assertExists()
    }

    //位置のテキストフィールドが表示されるか
    @Test
    fun showsPositionTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_position_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.person_position_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_position))).assertExists()
    }

    //スタイルのテキストフィールドが表示されるか
    @Test
    fun showsStyleTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_style_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.person_style_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_style))).assertExists()
    }

    //解像度のテキストフィールドが表示されるか
    @Test
    fun showsResolutionTextField() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.person_resolution_title)).assertExists()
        composeTestRule.onNodeWithText("例）" + composeTestRule.activity.getString(R.string.person_resolution_example)).assertExists()
        composeTestRule.onNode(hasText(composeTestRule.activity.getString(R.string.person_resolution))).assertExists()
    }
}
