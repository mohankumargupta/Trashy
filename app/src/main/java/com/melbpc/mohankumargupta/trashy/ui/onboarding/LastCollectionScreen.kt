package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.ui.components.TwoPaneDialog
import com.melbpc.mohankumargupta.trashy.ui.theme.TrashyTheme
import java.time.LocalDate

@Composable
fun LastCollectionScreen(
    onLastCollectionChosen: () -> Unit,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val lastCollectionDay = viewModel.uiState.collectAsState().value.collectionDay
    val today = LocalDate.now().dayOfWeek

    val isCollectionDayToday = lastCollectionDay.uppercase() == today.name

    LastCollectionComposable(
        lastCollectionDay = lastCollectionDay,
        isCollectionDayToday = isCollectionDayToday,
        onLastCollectionChosen = { lastCollection ->
            viewModel.handle(ScheduleIntent.LastBinType(lastCollection))
            onLastCollectionChosen()
        })
}

@Composable
fun LastCollectionComposable(
    lastCollectionDay: String? = null,
    onLastCollectionChosen: (BinType) -> Unit,
    isCollectionDayToday: Boolean = false,
) {
    val header = "Last Collection Bin"
    val instructionToday = """
        Need to know what bin collected today.
    """.trimIndent()
    val instructionLastCollection = """
        Need to know what bin was last collected last $lastCollectionDay.
    """.trimIndent()
    val instruction = if (isCollectionDayToday) instructionToday else instructionLastCollection

    TwoPaneDialog(
        title = header,
        text = instruction,
        options = listOf("Recycling", "Garden"),
        onOptionSelected = { binType ->
            onLastCollectionChosen(BinType.entries[binType])
        }
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(
    name = "720p",
    device = Devices.TV_720p,
)
fun LastCollectionPreview() {
    TrashyTheme(isInDarkTheme = true) {
        LastCollectionComposable(
            lastCollectionDay = "Monday",
            isCollectionDayToday = false,
            onLastCollectionChosen = {},
        )
    }
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(
    name = "720p",
    device = Devices.TV_720p,
)
fun CollectionTodayPreview() {
    TrashyTheme(isInDarkTheme = true) {
        LastCollectionComposable(
            lastCollectionDay = "Tuesday",
            isCollectionDayToday = true,
            onLastCollectionChosen = {},
        )
    }
}