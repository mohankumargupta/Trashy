package com.mohankumargupta.trashy

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.melbpc.mohankumargupta.trashy.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

object NodeIdentifiers {
    const val COLLECTION_DAY_SCREEN_TITLE = "Welcome to Trashy"
}

@RunWith(AndroidJUnit4::class)
class OnboardingTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun happyPath() {
        with(composeTestRule) {
           onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY_SCREEN_TITLE).assertExists()
        }
    }
}