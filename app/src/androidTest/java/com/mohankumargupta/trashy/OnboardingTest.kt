package com.mohankumargupta.trashy

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.melbpc.mohankumargupta.trashy.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

object NodeIdentifiers {
    const val COLLECTION_DAY_SCREEN_TITLE = "Welcome to Trashy"
    const val COLLECTION_DAY = "Tuesday"
    const val LAST_COLLECTION_SCREEN = "Last Collection Bin"
}

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class OnboardingTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun collectionDayScreen() {
        with(composeTestRule) {
            onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY_SCREEN_TITLE).assertExists()
            //onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY).performClick()

            // LAST COLLECTION SCREEN
            //onNodeWithContentDescription(NodeIdentifiers.LAST_COLLECTION_SCREEN).assertExists()
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
            waitForIdle()
            onNodeWithContentDescription(NodeIdentifiers.LAST_COLLECTION_SCREEN).assertExists()
        }

//        with(composeTestRule) {
//            val node = onNodeWithContentDescription(NodeIdentifiers.COLLECTION_DAY)
//            node.assertExists()
//            waitForIdle()
//
//            waitForIdle()
//            waitUntilAtLeastOneExists(
//                hasContentDescription(NodeIdentifiers.LAST_COLLECTION_SCREEN)
//                , timeoutMillis = 20_000
//            )
//        }
    }
}