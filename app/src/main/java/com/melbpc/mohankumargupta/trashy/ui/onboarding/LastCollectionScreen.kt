package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton
import com.melbpc.mohankumargupta.trashy.data.model.BinType

@Composable
fun LastCollectionScreen(
    modifier: Modifier = Modifier,
    onLastCollectionChosen: () -> Unit,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val lastCollectionDay = viewModel.uiState.collectAsState().value.collectionDay

    LastCollectionComposable(
        modifier = modifier,
        lastCollectionDay = lastCollectionDay,
        onLastCollectionChosen = { lastCollection ->
            viewModel.handle(ScheduleIntent.LastBinType(lastCollection))
            onLastCollectionChosen()
        }
    )
}

@Composable
fun LastCollectionComposable(
    modifier: Modifier = Modifier,
    lastCollectionDay: String? = null,
    onLastCollectionChosen: (BinType) -> Unit,
) {
    val header = "Last Collection Bin"
    val instructionToday = """
        Need to know what bin collected today.
    """.trimIndent()
    val instructionLastCollection = """
        Need to know what bin was last collected last $lastCollectionDay.
    """.trimIndent()
    val instruction = if (lastCollectionDay != null) instructionLastCollection else instructionToday

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(header, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = modifier.size(32.dp))
        Text(instruction)
        Spacer(modifier = modifier.size(64.dp))
        Row(
            modifier = modifier, horizontalArrangement = Arrangement.spacedBy(64.dp)
        ) {
            WideButton(
                modifier = modifier,
                onClick = {
                    onLastCollectionChosen(BinType.RECYCLING)
                },
            ) {
                Text("Recycling")
            }

            WideButton(
                modifier = modifier,
                onClick = {
                    onLastCollectionChosen(BinType.GARDEN)
                },
            ) {
                Text("Garden")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun LastCollectionPreview() {
    LastCollectionComposable(
        onLastCollectionChosen = {})
}

@Preview(showBackground = true)
@Composable
fun CollectionTodayPreview() {
    LastCollectionComposable(
        lastCollectionDay = "Monday",
        onLastCollectionChosen = {}
    )
}