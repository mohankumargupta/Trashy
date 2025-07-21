package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.MotionDurationScale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.res.painterResource
import com.melbpc.mohankumargupta.trashy.R

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
    onReset: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .focusable()
            .onKeyEvent { key ->
                if (key.type == KeyEventType.KeyUp) {
                    when (key.key) {
                        Key.DirectionCenter, Key.Enter -> {
                            onReset()
                            true
                        }
                       else -> false
                    }

                } else {
                    false
                }
            }

        ,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = viewModel.bin),
            contentDescription = "next collection bin",

        )

    }
}