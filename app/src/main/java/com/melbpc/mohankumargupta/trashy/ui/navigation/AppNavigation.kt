package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.melbpc.mohankumargupta.trashy.ui.onboarding.CollectionDay


//Routes
data object OnboardingCollectionDay
data object OnboardingLastCollectionType
data object OnboardingRecyclingLidColor
data object OnboardingGardenLidColor
data object Home

@Composable
fun AppNavigation() {
    val backStack = remember { mutableStateListOf<Any>(OnboardingCollectionDay) }

    NavDisplay(
      backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry(OnboardingCollectionDay) {
                CollectionDay()
            }

            entry(OnboardingLastCollectionType) {

            }

            entry(OnboardingRecyclingLidColor) {

            }

            entry(OnboardingGardenLidColor) {

            }

            entry(Home) {

            }
        }
    )
}

//@Composable
//fun OnboardingNavigation() {
//
//}
//
//@Composable
//fun HomeNavigation() {
//
//}