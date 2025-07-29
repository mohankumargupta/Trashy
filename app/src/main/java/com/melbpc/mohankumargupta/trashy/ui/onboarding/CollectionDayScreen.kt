package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
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
fun CollectionDayComposable(modifier: Modifier = Modifier, onDayChosen: (String) -> Unit) {
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    val firstFocus = remember { FocusRequester() }
    val heading = "Welcome to Trashy"
    val blurb = """
        Welcome to Trashy, an app that helps you track your next bin 
        collection.
               
        First, choose your collection day from the
        days of the week on the right.
    """.trimIndent()

    TwoPaneDialog(
        title = heading,
        text = blurb,
        options = days,
        onOptionSelected = { index ->
            onDayChosen(days[index])
        }
    )

    /*

    LaunchedEffect(Unit) {
        firstFocus.requestFocus()
    }

    Row(modifier = modifier.fillMaxSize()) {


        Box(
            modifier = modifier
                .fillMaxHeight()
                .weight(2f)
                .padding(horizontal = 16.dp), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                Text(heading, style = MaterialTheme.typography.headlineLarge)
                Text(blurb)
            }
        }


        LazyColumn(
            modifier = modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            itemsIndexed(days) { index, day ->
                WideButton(
                    onClick = { onDayChosen(day) },
                    modifier = if (index == 0) {
                        modifier.focusRequester(firstFocus)
                    } else {
                        modifier
                    }
                ) {
                    Text(day)
                }

            }
        }
    }

     */
}


@Composable
@OptIn(ExperimentalTvMaterial3Api::class)
@Preview(
    name = "720p",
    device = Devices.TV_720p,
)
fun CollectionDayPreview(modifier: Modifier = Modifier) {
    TrashyTheme(isInDarkTheme = true) {
        CollectionDayComposable(modifier = modifier, onDayChosen = {})
    }
}