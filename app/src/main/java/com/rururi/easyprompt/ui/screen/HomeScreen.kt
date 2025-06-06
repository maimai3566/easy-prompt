package com.rururi.easyprompt.ui.screen

import android.R.attr.onClick
import android.R.attr.text
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToPrompt: () -> Unit = {},
    onNavigateToPreset: () -> Unit = {}
) {
//    TestColorPicker()
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier
            .fillMaxSize()
    ) {

        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        Button(onClick =onNavigateToPrompt){
            Text(
                text = stringResource(R.string.btn_prompt),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        Button(onClick = onNavigateToPreset){
            Text(
                text = stringResource(R.string.btn_text),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        Button(onClick = onNavigateToPreset) {
            Text(
                text = stringResource(R.string.btn_object),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Text(
            text = "すべての設定は「任意」です。\n不要であれば「次へ」ボタンで飛ばしてください。",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.p_large))
        )
    }
}

@Composable
fun TestColorPicker() {
    var color by remember { mutableStateOf(Color.White) }   //選択している色
    val controller = rememberColorPickerController()        //色選択コントローラー

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
            .padding(dimensionResource(R.dimen.p_large))
    ) {
        Row {
            Box(    //選択している色を表示する場所（わかりやすくするため設置）
                modifier = Modifier
                    .size(width = 48.dp, height = 32.dp)
                    .background(color)
                    .border(1.dp, Color.Black)
//                    .clickable { color = controller.selectedColor.value }
            )
            HsvColorPicker(     //円形のピッカー
                controller = controller,
                onColorChanged = { color = it.color },  //選択した色を取得→Boxに渡す
                modifier = Modifier
                    .size(height = 250.dp, width = 250.dp)  //ピッカーの大きさ
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        BrightnessSlider(       //明暗スライダー
            modifier = Modifier
                .fillMaxWidth()
                .height(32.dp)
                .padding(horizontal = dimensionResource(R.dimen.p_small)),
            controller = controller,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EasyPromptTheme {
        HomeScreen()
    }
}