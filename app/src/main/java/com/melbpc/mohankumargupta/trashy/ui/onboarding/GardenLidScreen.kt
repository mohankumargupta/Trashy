package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import com.melbpc.mohankumargupta.trashy.ui.components.BinColor

@Composable
fun GardenLidScreen(modifier: Modifier = Modifier, onGardenLidColorChosen: (ColorSwatch) -> Unit) {
    BinColor(modifier, binType = BinType.GARDEN, onClick = onGardenLidColorChosen)
}