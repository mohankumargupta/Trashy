package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
    onReset: () -> Unit,
) {
    var showResetDialog by remember { mutableStateOf(false) }

    if (showResetDialog) {
       ResetScreen(modifier = modifier, onReset)
    } else {
        Box(
            modifier = modifier
                .fillMaxSize()
                .focusable()
                .onKeyEvent { key ->
                    if (key.type == KeyEventType.KeyUp) {
                        when (key.key) {
                            Key.DirectionCenter, Key.Enter -> {
                                //onReset()
                                showResetDialog = true
                                true
                            }

                            else -> false
                        }

                    } else {
                        false
                    }
                },
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(id = viewModel.bin),
                contentDescription = "next collection bin",

                )

        }
    }
}

