package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.melbpc.mohankumargupta.trashy.ui.components.ResetDialog
import com.melbpc.mohankumargupta.trashy.ui.components.TwoPaneDialog

@Composable
fun ResetScreen(modifier: Modifier = Modifier, onReset: () -> Unit) {
    TwoPaneDialog(
        title = "Reset settings",
        text = "Are you sure you want to reset settings?",
        options = listOf("Yes", "No"),
        selectedOption = 1,
        onOptionSelected = { selectedOption ->
            if (selectedOption == 0) {
                onReset()
            }
        }
    )
}