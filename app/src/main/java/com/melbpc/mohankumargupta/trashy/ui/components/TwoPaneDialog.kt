package com.melbpc.mohankumargupta.trashy.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.ListItem
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import androidx.tv.material3.darkColorScheme

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
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF000000),
                        Color(0xFF0A0A0A)
                    )
                )
            )
        ,
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.6f),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 72.dp, vertical = 48.dp),
            ) {
                DialogLHS(
                    modifier = Modifier.weight(1f),
                    title = title,
                    text = text
                )

                DialogRHS(
                    modifier = Modifier.weight(1f),
                    options = options,
                    selectedOption = selectedOption,
                    onOptionSelected = onOptionSelected
                )
            }
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun DialogLHS(
    modifier: Modifier = Modifier,
    title: String,
    text: String
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(end = 36.dp), // Add padding to create space between columns
        verticalArrangement = Arrangement.spacedBy(48.dp, Alignment.Top)
    ) {
        Text(title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.semantics { contentDescription = title }
        )
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun DialogRHS(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LazyColumn(
        modifier = modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
    ) {
        itemsIndexed(options) { index, option ->
            val isSelected = index == selectedOption
            val modifierSettings = Modifier.clip(RoundedCornerShape(12.dp))

            ListItem(
                modifier = if (isSelected) {
                    modifierSettings.focusRequester(focusRequester)
                } else {
                    modifierSettings
                },
                selected = isSelected,
                onClick = { onOptionSelected(index) },
                headlineContent = { Text(option) },
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(
    name = "720p",
    device = Devices.TV_720p,
    showSystemUi = false
)
@Preview(
    name = "1080p",
    device = Devices.TV_1080p,
    showSystemUi = false
)
@Composable
fun TwoPaneDialogPreviewAI() {
    // Wrap the preview in a TvMaterialTheme for proper component styling
    MaterialTheme(colorScheme = darkColorScheme()) {
        TwoPaneDialog(
            title = "Reset settings",
            text = "Are you sure you want to reset settings?",
            options = listOf("Yes", "No"),
            selectedOption = 0,
            onOptionSelected = {}
        )
    }
}