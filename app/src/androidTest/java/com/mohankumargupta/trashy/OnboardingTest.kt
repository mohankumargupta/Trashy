package com.mohankumargupta.trashy

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
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
    const val LAST_BIN_TYPE = "Recycling"
    const val RECYCLING_LID_COLOR_SCREEN = "Pick color for Recycling bin"
    const val GARDEN_LID_COLOR_SCREEN = "Pick color for Garden bin"
    const val PURPLE_LID = "Purple"
    const val BLUE_LID = "Blue"
    const val HOME_SCREEN_BACKGROUND = "Background Image"
}

@HiltAndroidTest
class OnboardingTest {
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun collectionDayScreen() {
        composeTestRule.apply {
            onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY_SCREEN_TITLE).assertIsDisplayed()
            onNodeWithContentDescription("Monday").assertIsFocused()
        }
    }

    private fun lastCollectionScreen() {
        composeTestRule.apply {
            onNodeWithContentDescription(NodeIdentifiers.LAST_COLLECTION_SCREEN).assertIsDisplayed()
        }
    }

    private fun recyclingLidColorScreen() {
        composeTestRule.apply {
            onNodeWithText(NodeIdentifiers.RECYCLING_LID_COLOR_SCREEN).assertIsDisplayed()
        }
    }

    private fun gardenLidColorScreen() {
        composeTestRule.apply {
            onNodeWithText(NodeIdentifiers.GARDEN_LID_COLOR_SCREEN).assertIsDisplayed()
        }
    }

    @OptIn(ExperimentalTestApi::class)
    private fun homeScreen() {
        composeTestRule.apply {
            waitUntilAtLeastOneExists(
            hasContentDescription(NodeIdentifiers.HOME_SCREEN_BACKGROUND),
                timeoutMillis = 10000
            )
            onNodeWithContentDescription(NodeIdentifiers.HOME_SCREEN_BACKGROUND).assertIsDisplayed()
        }
    }

    @Test
    fun initialScreen() {
        collectionDayScreen()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun onboarding() {
        collectionDayScreen()
        composeTestRule.apply {
            onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY).performSemanticsAction(
                SemanticsActions.OnClick
            )
        }
        lastCollectionScreen()

        composeTestRule.apply {
            onNodeWithText(NodeIdentifiers.LAST_BIN_TYPE).performSemanticsAction(
                SemanticsActions.OnClick
            )
        }

        recyclingLidColorScreen()
        composeTestRule.apply {
            onNodeWithContentDescription(NodeIdentifiers.BLUE_LID).performSemanticsAction(
                SemanticsActions.OnClick
            )
        }
        gardenLidColorScreen()
        composeTestRule.apply {
            onNodeWithContentDescription(NodeIdentifiers.PURPLE_LID).performSemanticsAction(
                SemanticsActions.OnClick
            )
        }

        homeScreen()
    }
}
