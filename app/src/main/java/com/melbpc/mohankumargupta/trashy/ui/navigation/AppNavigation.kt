package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.melbpc.mohankumargupta.trashy.ui.onboarding.CollectionDayScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.GardenLidScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.LastCollectionScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.RecyclingLidScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.ScheduleIntent
import com.melbpc.mohankumargupta.trashy.ui.onboarding.ScheduleViewModel

@Composable
fun AppNavigation(
    viewModel: ScheduleViewModel = hiltViewModel()
) {
    val navkey = viewModel.navKey
    val backStack = remember { mutableStateListOf<Any>(navkey.value) }


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
                CollectionDayScreen(
                    onDayChosen = { day ->
                        viewModel.handle(ScheduleIntent.DayChosen(day))
                        backStack.add(OnboardingLastCollectionType)
                    }
                )
            }

            entry(OnboardingLastCollectionType) {
                LastCollectionScreen(
                    onLastCollectionChosen = { collection ->
                        viewModel.handle(ScheduleIntent.LastBinType(collection))
                        backStack.add(OnboardingRecyclingLidColor)
                    }
                )
            }

            entry(OnboardingRecyclingLidColor) {
                RecyclingLidScreen(
                    onRecyclingLidColorChosen = { color ->
                        viewModel.handle(ScheduleIntent.RecyclingLidColor(color))
                        backStack.add(OnboardingGardenLidColor)
                    }
                )
            }

            entry(OnboardingGardenLidColor) {
                GardenLidScreen(
                    onGardenLidColorChosen = { color ->
                        viewModel.handle(ScheduleIntent.GardenLidColor(color))
                        backStack.add(Home)
                    }
                )
            }

            entry(Home) {

            }
        }
    )
}



