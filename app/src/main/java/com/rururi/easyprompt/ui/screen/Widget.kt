package com.rururi.easyprompt.ui.screen


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ext.toRgbaString
import com.rururi.easyprompt.utils.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorSelectDialog(
    initialColor: Color,
    onColorSelected: (Color) -> Unit,
    onDismiss: () -> Unit,
) {
    var color by remember { mutableStateOf(initialColor) }
    val controller = rememberColorPickerController()

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {onColorSelected(color)}) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        },
        title = { Text("Select a color") },
        text = {
            Column {
                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    controller = controller,
                    onColorChanged = {
                        color = it.color
                    }
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_medium)))
                BrightnessSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .padding(horizontal = dimensionResource(R.dimen.p_small)),
                    controller = controller,
                )
            }
            Box(
                modifier = Modifier
                    .size(width = 48.dp, height = 32.dp)
                    .background(color)
                    .clickable { onColorSelected(color) }
            )
        }
    )
}

val TestColorKey = SemanticsPropertyKey<String>("TestColor")
var SemanticsPropertyReceiver.testColor: String by TestColorKey

@Composable
fun SetColor(
    modifier: Modifier = Modifier,
    title:String,
    onColorSelected: (Color) -> Unit,
    selectedColor: Color = Color.White,
    showTitle:Boolean = true,
){
    var showColorDialog by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        log("ボタンの色: ${selectedColor.toRgbaString()}")
        Text(
            text = "■$title",
            style = MaterialTheme.typography.titleLarge,
            color = if (showTitle) Color.Black else Color.Transparent
        )
        Button(
            onClick = { showColorDialog = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = selectedColor,
                contentColor = if (selectedColor.luminance() >0.5) Color.Black else Color.White,
            ),
            border = BorderStroke(1.dp, Color.Black),
            modifier = Modifier
                .padding(dimensionResource(R.dimen.p_small))

        ) {
            Text(text = stringResource(R.string.select_color))
        }
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_medium)))
    }

    if (showColorDialog) {
        ColorSelectDialog(
            initialColor = selectedColor,
            onColorSelected = {
                onColorSelected(it)
                showColorDialog = false
            },
            onDismiss = { showColorDialog = false }
        )
    }
}

@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderId: Int,
    singleLine: Boolean,
    maxLines: Int,
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(placeholderId)) },
        singleLine = singleLine,
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,         //Enterを押したら次の行へ
            keyboardType = KeyboardType.Text    //テキスト入力用
        ),  //次の入力に移動
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },   //Enterを押したら次の行に移動
            onDone = { focusManager.clearFocus() }  //キーボードを閉じる
        ),
        modifier = modifier
            .fillMaxWidth()
            .testTag("commonTextField")
    )
}
@Composable
fun SetTextField(
    modifier: Modifier = Modifier,
    title:String,
//    example:String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderId:Int,
    singleLine:Boolean = true,
    maxLines:Int = 1,
){
    Column(modifier=modifier) {
        Text(text = "■$title", style = MaterialTheme.typography.titleLarge)
        Column(modifier = modifier.padding(dimensionResource(R.dimen.p_small))) {
            CommonTextField(
                value = value,
                onValueChange = onValueChange,
                placeholderId = placeholderId,
                singleLine = singleLine,
                maxLines = maxLines
            )
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_medium)))
        }
    }
}

@Composable
fun SetRadioText(
    modifier: Modifier = Modifier,
    title:String,
    options: List<RadioOption>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
//    example:String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderId:Int,
    singleLine:Boolean = true,
    maxLines:Int = 1,
){
    Column(modifier = modifier) {
        Text(text = "■$title", style= MaterialTheme.typography.titleLarge)
        Column(modifier = modifier.padding(dimensionResource(R.dimen.p_small))) {
            RadioSelector(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected,
            )
            val labelText = stringResource(R.string.option_custom)
            if (selectedOption == labelText) {      //自分で選択を選んだ時にテキストフィールドを表示
                CommonTextField(
                    value = value,
                    onValueChange = onValueChange,
                    placeholderId = placeholderId,
                    singleLine = singleLine,
                    maxLines = maxLines,
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_medium)))
        }
    }
}
@Composable
fun SetRadio(
    modifier: Modifier = Modifier,
    title:String,
    options: List<RadioOption>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
){
    Column(modifier = modifier) {
        Text(text = "■$title", style = MaterialTheme.typography.titleLarge)
        Column(modifier = modifier.padding(dimensionResource(R.dimen.p_small))) {
            RadioSelector(
                options = options,
                selectedOption = selectedOption,
                onOptionSelected = onOptionSelected,
            )
        }
    }
}

@Composable
fun RadioSelector(
    modifier: Modifier = Modifier,
    options: List<RadioOption>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit,
) {
    val content: @Composable (RadioOption) -> Unit = { option ->
        val labelText = stringResource(option.labelResId)
        val isSelected = labelText == selectedOption //自分が選択されたかどうか
        val rowModifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.p_small))
            .clickable {
                log("クリックした！！！: $labelText")
                onOptionSelected(labelText) }
            .testTag(option.tag)
            .semantics { this.selected = isSelected }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
        ) {
            RadioButton(
                selected = isSelected,
                onClick = null,
            )
            Text(
                text = stringResource(option.labelResId),
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_small)))
        }
    }
    FlowRow(modifier = modifier) {
        options.forEach { option ->
            content(option)
        }
    }
}

//ラジオボタンのデータクラス
data class RadioOption(
    val labelResId: Int,          //ラベル多言語対応
    val tag: String = ""         //テスト対応
)

//反対色を取得する拡張関数
fun Color.invert():Color {
    return Color(
        red = 1f - red,
        green =1f - green,
        blue = 1f - blue,
        alpha = alpha
    )
}

@Preview(showBackground = true)
@Composable
fun Prev() {
    ColorSelectDialog(
        initialColor = Color.White,
        onColorSelected = {},
        onDismiss = {}
    )
}