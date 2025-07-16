package com.melbpc.mohankumargupta.trashy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
//import androidx.tv.material3.Text
//import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
//import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.Surface
import com.melbpc.mohankumargupta.trashy.ui.navigation.AppNavigation
import com.melbpc.mohankumargupta.trashy.ui.theme.TrashyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrashyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RectangleShape
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
