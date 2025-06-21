package com.rururi.easyprompt

// Mockk のインポート
import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.rururi.easyprompt.ui.screen.prompt.CanvasSec
import com.rururi.easyprompt.ui.screen.prompt.PromptViewModel
import com.rururi.easyprompt.utils.log
import org.junit.Before
import org.junit.Rule
import org.junit.Test

val TestColorKey = SemanticsPropertyKey<String>("TestColor")
var SemanticsPropertyReceiver.testColor: String by TestColorKey

class CanvasSecTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    //後でこのviewModelをmockkを使ってモック化
    private lateinit var viewModel: PromptViewModel

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val vm = remember { PromptViewModel() }
            viewModel = vm
            val uiState by vm.uiState.collectAsState()
            CanvasSec(viewModel = vm, uiState = uiState)
        }
    }

    //キャンバスの大きさのラジオボタンが正しく表示されるか
    @Test
    fun showsCanvasSizeOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_size_title)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_size_1)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_size_2)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_size_3)).assertExists()
    }

    //背景の種類のラジオボタンが正しく表示されるか
    @Test
    fun showsBackgroundTypeOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_type_title)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_type_1)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_type_2)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_type_3)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_type_4)).assertExists()
        composeTestRule.onAllNodesWithText(
            composeTestRule.activity.getString(R.string.option_custom)
        ).assertCountEquals(2)
    }

    //背景の色の色選択ボタンが正しく表示されるか
    @Test
    fun showsBackgroundColorButton() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_color_title)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.select_color)).assertExists()
    }

    //背景のエフェクトのラジオボタンが正しく表示されるか
    @Test
    fun showsBackgroundEffectOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_effect_title)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_effect_1)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_effect_2)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_effect_3)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_effect_4)).assertExists()
        //option_customが2つあることは背景の種類で確認済みなのでここでは割愛
    }

    //キャンバスの余白が正しく設定されているか
    @Test
    fun showsCanvasPadding() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_padding_title)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_padding_1)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_padding_2)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_padding_3)).assertExists()
    }

    //オーバーレイのラジオボタンが正しく表示されるか
    @Test
    fun showsOverlayOption() {
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_overlay_title)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_overlay_1)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_overlay_2)).assertExists()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.canvas_overlay_3)).assertExists()
    }

    //キャンバスの大きさのラジオボタンをクリックしたときに、選択したものにチェックが入っているか
    @Test
    fun updatesCanvasSizeWhenOptionSelected() {
        val tag = "canvasSize2"
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).performClick()     //9:16という文字列をクリックしてる
        composeTestRule.waitForIdle()   //待つ
        composeTestRule.runOnIdle {
            val selected = viewModel.uiState.value.canvasState.size     //uiStateの値を取得
            log("selected: $selected")
            assert(selected == composeTestRule.activity.getString(R.string.canvas_size_2))  //uiStateの値が9:16になったことをアサート
        }
        composeTestRule.onNodeWithTag(tag,useUnmergedTree = true).assertIsSelected()    //9:16にチェックが入っていることをアサート
    }

    //背景種類が「自分で設定」のとき、テキストフィールドが表示されるか
    @Test
    fun showsCustomTextfieldWhenCustomSelected() {
        composeTestRule.onNodeWithTag("canvasTypeCustom",useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("commonTextField").assertExists()
    }

    //背景のエフェクトが「自分で設定」のとき、テキストフィールドが表示されるか
    @Test
    fun showsCustomTextfieldWhenEffectCustomSelected() {
        composeTestRule.onNodeWithTag("canvasEffectCustom",useUnmergedTree = true).performClick()
        composeTestRule.onNodeWithTag("commonTextField").assertExists()
    }

    //背景の種類が「透明」の時、背景の色が非表示となるか
    @Test
    fun hidesBackgroundColorWhenTransparent() {
        composeTestRule.clickOption(R.string.canvas_type_1)
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_color_title)).assertDoesNotExist()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.select_color)).assertDoesNotExist()
    }

    //背景の種類が「グラデーション」の時、背景の色が2つ表示されるか
    @Test
    fun twoButtonsShowWhenGradient() {
        composeTestRule.clickOption(R.string.canvas_type_3)
        composeTestRule.onNodeWithText("■" + composeTestRule.activity.getString(R.string.canvas_color_title)).assertExists()
        composeTestRule.onAllNodesWithText(composeTestRule.activity.getString(R.string.select_color)).assertCountEquals(2)
    }

    //ラジオボタンをクリックする汎用関数
    fun AndroidComposeTestRule<*, ComponentActivity>.clickOption(res: Int) {
        onNodeWithText(activity.getString(res)).performClick()
    }
}
