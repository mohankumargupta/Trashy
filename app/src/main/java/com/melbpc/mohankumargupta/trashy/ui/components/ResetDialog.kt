package com.melbpc.mohankumargupta.trashy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@Composable
fun ResetDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onConfirm: () -> Unit = {},
    title: String,
    text: String,
    confirmText: String = "OK",
    dismissText: String = "Cancel"
) {
    Row(modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .weight(1.3f)
                .padding(top = 48.dp)
            ,
        ) {
            ResetDialogLHS(
                modifier = modifier,
                title,
                text
            )
        }
        Box(
            modifier = modifier
                .fillMaxHeight()
                .background(Color.DarkGray.copy(alpha = 0.6f))
                .weight(0.7f),
            contentAlignment = Alignment.Center,
        ) {
            ResetDialogRHS(
                modifier = Modifier,
                confirmText,
                dismissText,
                onConfirm,
                onDismissRequest
            )
        }
    }
}

@Composable
fun ResetDialogLHS(modifier: Modifier = Modifier, title: String, prompt: String) {
    Column(
        modifier = modifier,

    ) {
        Text(
            title,
            modifier = modifier
                .padding(bottom = 32.dp),
            style = MaterialTheme.typography.headlineLarge,
            color = Color.White
        )
        Text(prompt, style = MaterialTheme.typography.bodyLarge, color = Color.Red)
    }

}

@Composable
fun ResetDialogRHS(
    modifier: Modifier = Modifier,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Text(confirmText, style = MaterialTheme.typography.bodyLarge, color = Color.White)
        Spacer(modifier = Modifier.size(32.dp))
        Text(dismissText, style = MaterialTheme.typography.bodyLarge, color = Color.White)
    }
}

@Preview
@Composable
fun ResetDialogPreview(modifier: Modifier = Modifier) {
    ResetDialog(title="Reset settings", text="Are you sure you want to reset settings?", modifier = modifier)
}