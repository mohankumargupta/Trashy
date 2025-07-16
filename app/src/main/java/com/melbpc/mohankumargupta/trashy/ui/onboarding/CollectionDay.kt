package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import androidx.tv.material3.WideButton

@Composable
fun CollectionDay(modifier: Modifier = Modifier) {
    val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
    var selectedDay by remember { mutableStateOf<String>(days.first()) }
    val firstFocus = remember { FocusRequester() }
    val heading = "Trashy"
    val blurb = """
        Welcome to Trashy, an app that helps you track your next bin 
        collection.
               
        First, choose your collection day from the
        days of the week on the right.
    """.trimIndent()

    Row(modifier = modifier.fillMaxSize()) {


        Box(
            modifier = modifier
                .fillMaxHeight()
                .weight(2f), contentAlignment = Alignment.Center
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
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            itemsIndexed(days) { index, day ->
                WideButton(
                    onClick = {},
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
}