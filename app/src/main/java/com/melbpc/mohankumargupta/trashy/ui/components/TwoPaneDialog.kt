package com.melbpc.mohankumargupta.trashy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices.TV_1080p
import androidx.compose.ui.tooling.preview.Devices.TV_720p
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ListItem
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text

@Composable
fun TwoPaneDialog(
    title: String,
    text: String,
    options: List<String>,
    selectedOption: Int = 0,
    onOptionSelected: (Int) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(
                top = 192.dp,
                start = 192.dp,
                end = 192.dp,
            )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                DialogLHS(
                    modifier = Modifier.weight(1f),
                    title = title,
                    text = text
                )
                DialogRHS(
                    modifier = Modifier
                        .weight(1f),
                    options = options,
                    selectedOption = selectedOption,
                    onOptionSelected = onOptionSelected,
                )
            }
        }
    }

}

@Composable
fun DialogLHS(modifier: Modifier, title: String, text: String) {
    Column(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(48.dp, Alignment.Top)
    ) {
        Text(title, style = MaterialTheme.typography.headlineLarge, color = Color.White)
        Text(text, style = MaterialTheme.typography.bodyLarge, color = Color.White)
    }
}

@Composable
fun DialogRHS(
    modifier: Modifier,
    options: List<String>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(48.dp, Alignment.Top),
        ) {
        itemsIndexed(options) { index, option ->
            ListItem(
                selected = index == selectedOption,
                headlineContent = {
                    Text(
                        option,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White
                    )
                },
                onClick = { },
            )
        }
    }
}

@Preview(
    name = "720p",
    showBackground = false,
    backgroundColor = 0xFF000000,
    device = TV_720p,
    showSystemUi = false
)
@Preview(
    name = "1080p",
    showBackground = false,
    backgroundColor = 0xFF000000,
    device = TV_1080p,
    showSystemUi = false
)
@Composable
fun TwoPaneDialogPreview() {
    TwoPaneDialog(
        title = "Reset settings",
        text = "Are you sure you want to reset settings?",
        options = listOf("Yes", "No"),
        selectedOption = 0,
        onOptionSelected = {}
    )
}