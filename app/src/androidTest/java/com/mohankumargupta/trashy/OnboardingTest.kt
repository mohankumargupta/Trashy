package com.mohankumargupta.trashy

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performSemanticsAction
import com.melbpc.mohankumargupta.trashy.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

object NodeIdentifiers {
    const val COLLECTION_DAY_SCREEN_TITLE = "Welcome to Trashy"
    const val COLLECTION_DAY = "Tuesday"
    const val LAST_COLLECTION_SCREEN = "Last Collection Bin"
}

@HiltAndroidTest
class OnboardingTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun collectionDayScreen() {
        composeTestRule.apply {
            onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY_SCREEN_TITLE).assertExists()
        }
    }

    @Test
    fun initialScreen() {
        collectionDayScreen()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun lastCollectionDayScreen() {
        collectionDayScreen()
        composeTestRule.apply {
            onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY).performSemanticsAction(
                SemanticsActions.OnClick)
            onNodeWithContentDescription(NodeIdentifiers.LAST_COLLECTION_SCREEN).assertExists()
        }
    }
}
