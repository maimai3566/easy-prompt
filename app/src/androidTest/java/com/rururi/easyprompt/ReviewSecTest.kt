package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.ui.screen.prompt.ReviewSec
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ReviewSecTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            val uiState by vm.uiState.collectAsState()
            ReviewSec(viewModel = vm, uiState = uiState)
        }
    }

    //タイトルや説明文が表示されること
    @Test
    fun displaysPreviewTitle() {
        composeTestRule.showsText(R.string.preview_title)
        composeTestRule.showsText(R.string.preview_memo_1)
        composeTestRule.showsText(R.string.preview_memo_2)
    }

    //初期値の確認：テキストフィールドは編集不可、編集ボタンは編集不可
    @Test
    fun initialValues() {
        composeTestRule.onNodeWithTag("previewTextField",useUnmergedTree = true).assert(hasSetTextAction().not())
        composeTestRule.showsText(R.string.btn_edit)
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.btn_edit)
        ).assertExists()
    }

    //編集ボタンを押すとテキストフィールドは編集可能、編集ボタンは編集中！
    @Test
    fun editButtonClick() {
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.btn_edit)
        ).performClick()
        composeTestRule.onNodeWithTag("previewTextField",useUnmergedTree = true).assert(hasSetTextAction())
        composeTestRule.showsText(R.string.btn_edited)
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.btn_edited)
        ).assertExists()
    }

    //汎用関数：テキストが表示されるか
    fun AndroidComposeTestRule<*, ComponentActivity>.showsText(res: Int) {
        onNodeWithText(activity.getString(res)).assertExists()
    }

    //汎用関数：タグが存在するか
    fun AndroidComposeTestRule<*, ComponentActivity>.showsTag(tag: String) {
        onNodeWithTag(tag,useUnmergedTree = true).assertExists()
    }
}
