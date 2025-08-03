package com.melbpc.mohankumargupta.trashy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.tv.material3.Surface
import com.melbpc.mohankumargupta.trashy.ui.navigation.AppNavigation
import com.melbpc.mohankumargupta.trashy.ui.theme.TrashyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepSplash = true
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition {
            keepSplash
        }

        lifecycleScope.launch {
            delay(5000)
            keepSplash = false
        }


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
