package com.melbpc.mohankumargupta.trashy.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Border
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.Text
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch

@Composable
fun BinColor(modifier: Modifier = Modifier, binType: BinType, onClick: (Color) -> Unit) {
    val binName = if (binType == BinType.RECYCLING) "Recycling" else "Garden"
    val colors = ColorSwatch.entries.chunked(4)

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
       item {
           Text(
               text = "Pick color for $binName bin",
               color = Color.White,
               style = MaterialTheme.typography.headlineLarge,
               modifier = Modifier.padding(bottom = 36.dp)
           )
       }

        items(colors) { rowColors ->
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(rowColors) { color ->
                    ColorButton(
                        color = color.color,
                        onSelectColor = { onClick(color.color) }
                    )
                }
            }

        }
    }

}

@Composable
fun ColorButton(
    modifier: Modifier = Modifier,
    color: Color,
    onSelectColor: () -> Unit

) {
    OutlinedButton(
        modifier = modifier
            .padding(12.dp)
            .size(50.dp),
        shape = ButtonDefaults.shape(CircleShape),
        border = ButtonDefaults.border(
            Border(
                BorderStroke(
                    width = 1.dp,
                    color = color
                )
            )
        ),
        onClick = {
            onSelectColor()
        },
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun BinColorPreview(modifier: Modifier = Modifier) {
  BinColor(binType = BinType.RECYCLING, onClick = {})
}


