package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.ui.components.BinColor

@Composable
fun RecyclingLidScreen(modifier: Modifier = Modifier, onRecyclingLidColorChosen: (Color) -> Unit) {
    BinColor(modifier = modifier, binType = BinType.RECYCLING, onClick = onRecyclingLidColorChosen)
}