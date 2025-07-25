package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.melbpc.mohankumargupta.trashy.ui.components.ResetDialog

@Composable
fun ResetScreen(modifier: Modifier = Modifier, onReset: () -> Unit) {
    ResetDialog(
        modifier = modifier,
        title = "Reset settings",
        text = "Are you sure you want to reset settings?",
        onConfirm = onReset,
    )
}