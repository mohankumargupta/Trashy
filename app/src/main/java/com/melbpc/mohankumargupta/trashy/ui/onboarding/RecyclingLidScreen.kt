package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import com.melbpc.mohankumargupta.trashy.ui.components.BinColor

@Composable
fun RecyclingLidScreen(modifier: Modifier = Modifier, onRecyclingLidColorChosen: (ColorSwatch) -> Unit) {
    BinColor(modifier = modifier, binType = BinType.RECYCLING, onClick = onRecyclingLidColorChosen)
}