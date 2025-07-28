package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.TV_720p
import androidx.compose.ui.tooling.preview.Preview
import com.melbpc.mohankumargupta.trashy.R

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    onReset: () -> Unit,
) {
    HomeComposable(viewModel.bin, onReset)
}

@Composable
fun HomeComposable(@DrawableRes bin:  Int?, onReset: () -> Unit) {
    var showResetDialog by remember { mutableStateOf(false) }

    Box {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        if (showResetDialog) {
            ResetScreen(modifier = Modifier, onReset)
        } else {
            Box(
                modifier = Modifier
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
                bin?.let { nextBin ->
                    Image(
                        painter = painterResource(id = nextBin),
                        contentDescription = "next collection bin",
                    )
                }

            }
        }
    }
}

@Preview(device = TV_720p)
@Composable
fun HomeLoadingPreview() {
    HomeComposable(
        bin = null,
        onReset = {}
    )
}

@Preview(device = TV_720p)
@Composable
fun HomePreview() {
    HomeComposable(
        bin = R.drawable.recycling_bin_black,
        onReset = {}
    )
}


