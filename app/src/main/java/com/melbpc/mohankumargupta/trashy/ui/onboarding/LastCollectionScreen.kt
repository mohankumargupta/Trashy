package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton
import com.melbpc.mohankumargupta.trashy.data.model.BinType

@Composable
fun LastCollectionScreen(modifier: Modifier = Modifier, onLastCollectionChosen: (BinType) -> Unit) {

    val header = "Last Collection Bin"
    val instruction = """
        Need to know what bin was collected.
    """.trimIndent()

    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
        Text(header, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = modifier.size(32.dp))
        Text(instruction)
        Spacer(modifier = modifier.size(64.dp))
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(64.dp)
        ) {
            WideButton(
                modifier = modifier,
                onClick = { },
            ) {
                Text("Recycling")
            }

            WideButton(
                modifier = modifier,
                onClick = { },
            ) {
                Text("Garden")
            }


        }
    }
}