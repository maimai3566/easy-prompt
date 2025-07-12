package com.rururi.easyprompt

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EasyPromptAppTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule< ComponentActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            navController = TestNavHostController(composeTestRule.activity)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            // EasyPromptApp(promptViewModel = PromptViewModel())
        }
    }

    //ホームボタンを押したとき
    @Test
    fun homeButton_returnsToHome() {
        composeTestRule.clickButton(R.string.btn_prompt)
        composeTestRule.onNodeWithContentDescription(
            composeTestRule.activity.getString(R.string.sc_home)
        ).performClick()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_prompt)
        ).assertExists()
    }

    //人物中心
    @Test
    fun personSec_showsSectionNameAndButton() {
        composeTestRule.clickButton(R.string.btn_prompt)

        //Canvasには「次へ」ボタンしかない
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertDoesNotExist()
        composeTestRule.clickButton(R.string.btn_next)

        composeTestRule.onNodeWithText("★ Camera").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)

        composeTestRule.onNodeWithText("★ Theme").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)

        composeTestRule.onNodeWithText("★ Lighting").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)

        composeTestRule.onNodeWithText("★ Person").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)

        composeTestRule.onNodeWithText("★ Review").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_copy)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()

        //戻るボタンを押すと1つ前の画面に戻る
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Person").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Lighting").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Theme").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Camera").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
    }

    //次へや戻るボタンをクリックしたとき
    @Test
    fun personSec_buttonClick() {
        //次へボタンをクリック
        composeTestRule.clickButton(R.string.btn_prompt)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)

        //戻るボタンを押すと1つ前の画面に戻る
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Person").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Lighting").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Theme").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Camera").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
    }

    //テキスト中心の画面遷移テスト
    @Test
    fun textSec_showsSectionNameAndButton() {
        composeTestRule.clickButton(R.string.btn_text)
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertDoesNotExist()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Camera").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Theme").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Title").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Body").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Review").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_copy)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
    }

    @Test
    fun textSec_buttonClick() {
        composeTestRule.clickButton(R.string.btn_text)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)

        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Body").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Title").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Theme").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Camera").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
    }
    //背景中心
    @Test
    fun backgroundSec_showsSectionNameAndButton() {
        composeTestRule.clickButton(R.string.btn_background)
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertDoesNotExist()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Camera").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Theme").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Lighting").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_next)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.onNodeWithText("★ Review").assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_copy)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_back)
        ).assertExists()
    }

    @Test
    fun backgroundSec_buttonClick() {
        composeTestRule.clickButton(R.string.btn_background)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)
        composeTestRule.clickButton(R.string.btn_next)

        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Lighting").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Theme").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Camera").assertExists()
        composeTestRule.clickButton(R.string.btn_back)
        composeTestRule.onNodeWithText("★ Canvas").assertExists()
    }

    //コピーボタンを押したときにボタンの名前がコピーした！に変わり、2秒後にコピーに戻る
    @Test
    fun copyButton_copiesText() {
        composeTestRule.clickButton(R.string.btn_prompt)    //人物中心をクリック
        repeat(5){ composeTestRule.clickButton(R.string.btn_next) } //次へボタンを5回クリック
        composeTestRule.onNodeWithText("★ Review").assertExists()   //Reviewに来たことを確認
        composeTestRule.clickButton(R.string.btn_copy)  //コピーを押す
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_copied) //コピーした！に変わったことを確認
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_copy)   //コピーはない
        ).assertDoesNotExist()
        //3秒待つ
        composeTestRule.waitUntil(timeoutMillis = 3000) {
            composeTestRule.onAllNodesWithText(
                composeTestRule.activity.getString(R.string.btn_copy)
            ).fetchSemanticsNodes().isNotEmpty()
        }
        //コピーが復活し、コピーした！がなくなっていることを確認
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_copy)
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.btn_copied)
        ).assertDoesNotExist()
    }

    //リセットボタンを押したときに確認のダイアログが表示されるか
    @Test
    fun resetButton_showsConfirmationDialog() {
        //リセットをクリック
        composeTestRule.clickButton(R.string.btn_full_reset)
        //ダイアログが表示されたか
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.dialog_reset)
        ).assertExists()
        //キャンセルをクリック
        composeTestRule.clickButton(R.string.btn_cancel)
        //ダイアログが消えたか
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.dialog_reset)
        ).assertDoesNotExist()

        //リセットをクリック
        composeTestRule.clickButton(R.string.btn_full_reset)
        //ダイアログのリセットをクリック
        composeTestRule.clickButton(R.string.dialog_reset)
        //ダイアログが消えたか
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.dialog_reset)
        ).assertDoesNotExist()
    }

    fun AndroidComposeTestRule<*, ComponentActivity>.clickButton(res: Int) {
        onNodeWithText(activity.getString(res)).performClick()
    }
}

