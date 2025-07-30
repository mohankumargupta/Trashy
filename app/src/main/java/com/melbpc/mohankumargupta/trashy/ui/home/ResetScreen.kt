package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.compose.runtime.Composable
import com.melbpc.mohankumargupta.trashy.ui.components.TwoPaneDialog

@Composable
fun ResetScreen(onConfirm: () -> Unit, onCancel: () -> Unit) {
    TwoPaneDialog(
        title = "Reset settings",
        text = "Are you sure you want to reset settings?",
        options = listOf("Yes", "No"),
        selectedOption = 1,
        onOptionSelected = { selectedOption ->
            if (selectedOption == 0) {
                onConfirm()
            }
            else {
                onCancel()
            }
        }
    )
}