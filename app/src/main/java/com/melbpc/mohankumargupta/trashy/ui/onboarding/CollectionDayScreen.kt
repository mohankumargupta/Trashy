package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import com.melbpc.mohankumargupta.trashy.ui.components.TwoPaneDialog
import com.melbpc.mohankumargupta.trashy.ui.theme.TrashyTheme

@Composable
fun CollectionDayScreen(
    onDayChosen: () -> Unit,
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    CollectionDayComposable(onDayChosen = { collectionDay ->
        viewModel.handle(ScheduleIntent.DayChosen(collectionDay))
        onDayChosen()
    })
}

@Composable
fun CollectionDayComposable(onDayChosen: (String) -> Unit) {
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val heading = "Welcome to Trashy"
    val blurb = """
        Trashy will tell your next bin collection.
               
        Choose your collection day.
    """.trimIndent()

    TwoPaneDialog(
        title = heading,
        text = blurb,
        options = days,
        onOptionSelected = { index ->
            onDayChosen(days[index])
        }
    )
}

@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(
    name = "720p",
    device = Devices.TV_720p,
)
fun CollectionDayPreview() {
    TrashyTheme(isInDarkTheme = true) {
        CollectionDayComposable(onDayChosen = {})
    }
}

