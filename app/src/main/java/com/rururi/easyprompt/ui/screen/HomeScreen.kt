package com.rururi.easyprompt.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale.Companion.Crop
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToPerson: () -> Unit = {},
    onNavigateToText: () -> Unit = {},
    onNavigateToBackground: () -> Unit = {},
    onResetAll: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }

    //フルリセット時の確認ダイアログ
    if (showDialog) {
        BasicAlertDialog(
            onDismissRequest = { showDialog = false },  //キャンセルボタンや画面外をクリックしたときの処理
            content = {     //ダイアログに表示させるコンテンツ
                ResetDialogContent(
                    onDismiss = { showDialog = false },
                    onConfirm = {
                        showDialog = false
                        onResetAll()
                    }
                )
            }
        )
    }
    //本体
    Box(modifier = Modifier.fillMaxSize()){
        Image(
            painter = painterResource(R.drawable.home_image),
            contentDescription = null,
            contentScale = Crop,
//            alpha = 0.3f,
            modifier = Modifier.fillMaxSize()
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = Modifier.padding(dimensionResource(R.dimen.p_large))
        )
    }
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier=modifier
            .fillMaxSize()
    ) {
        Button(onClick = onNavigateToPerson){
            Text(
                text = stringResource(R.string.btn_prompt),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        Button(onClick = onNavigateToText){
            Text(
                text = stringResource(R.string.btn_text),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        Button(onClick = onNavigateToBackground) {
            Text(
                text = stringResource(R.string.btn_background),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Text(
            text = "すべての設定は「任意」です。\n不要であれば「次へ」ボタンで飛ばしてください。",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.p_large))
        )
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
        OutlinedButton(onClick = { showDialog = true }) { //リセット用のダイアログを表示
            Text(
                text = stringResource(R.string.btn_full_reset),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
    }
}

@Composable
fun ResetDialogContent(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
){
    Surface(
        shape = MaterialTheme.shapes.small, //ダイアログの角の形状
        color = MaterialTheme.colorScheme.surface,  //背景色
        tonalElevation = dimensionResource(R.dimen.p_small) //影の深さ
    ) {

        Column(
            modifier = modifier.padding(dimensionResource(R.dimen.p_medium))
        ){
            Text(
                text = stringResource(R.string.dialog_title),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.dialog_message),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_medium)))
            Row {
                OutlinedButton(onClick = onDismiss) {
                    Text(
                        text = stringResource(R.string.btn_cancel),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Spacer(modifier = Modifier.padding(dimensionResource(R.dimen.p_small)))
                Button(onClick = onConfirm) {
                    Text(
                        text = stringResource(R.string.dialog_reset),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    EasyPromptTheme {
        ResetDialogContent(onDismiss = {}, onConfirm = {})
    }
}
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    EasyPromptTheme {
        HomeScreen()
    }
}
