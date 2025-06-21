package com.rururi.easyprompt.ui.screen.prompt

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rururi.easyprompt.R
import com.rururi.easyprompt.ui.theme.EasyPromptTheme

@Composable
fun ReviewSec(
    modifier: Modifier = Modifier,
    viewModel: PromptViewModel,
    uiState: PromptUiState
) {
    val isEdit = uiState.isEdit

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.p_small))
    ) {
        Text(
            text = stringResource(R.string.preview_title),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_small)))
        Text(
            text = stringResource(R.string.preview_memo_1),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = stringResource(R.string.preview_memo_2),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.p_small)))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            //プロンプト表示フィールド
            TextField(
                value = uiState.result,
                onValueChange = { viewModel.updateUiState{ copy(result = it) } },
                enabled = isEdit,
                textStyle = MaterialTheme.typography.bodyLarge,
                singleLine = false,
                maxLines = Int.MAX_VALUE,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .testTag("previewTextField")
            )
            //編集するボタン
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(dimensionResource(R.dimen.p_small))
                    .clickable { viewModel.updateUiState { copy(isEdit = !isEdit) } },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = if (isEdit) Icons.Default.Check else Icons.Default.Edit,
                    contentDescription = if (isEdit) stringResource(R.string.btn_edited) else stringResource(
                        R.string.btn_edit
                    ),
                )
                Text(
                    text = if (isEdit) stringResource(R.string.btn_edited) else stringResource(R.string.btn_edit),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ReviewSecPreview() {
    EasyPromptTheme {
        ReviewSec(viewModel = PromptViewModel(), uiState = PromptUiState())
    }
}