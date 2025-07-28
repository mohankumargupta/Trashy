package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import com.melbpc.mohankumargupta.trashy.ui.home.HomeScreen
import com.melbpc.mohankumargupta.trashy.ui.home.HomeScreenViewModel
import com.melbpc.mohankumargupta.trashy.ui.onboarding.CollectionDayScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.GardenLidScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.LastCollectionScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.RecyclingLidScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.ScheduleIntent
import com.melbpc.mohankumargupta.trashy.ui.onboarding.ScheduleViewModel
import androidx.compose.runtime.collectAsState

@Composable
fun AppNavigation(
    viewModel: ScheduleViewModel = hiltViewModel(),
    navViewModel: NavigationViewModel = hiltViewModel(),
) {
    val backStack = remember { mutableStateListOf<NavigationRoute>(RouteInitialScreen) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {

            entry<RouteInitialScreen> {
                val initialRoute by navViewModel.initialRoute.collectAsStateWithLifecycle()
                when (initialRoute) {
                    is RouteHome -> {
                        HomeScreen(
                            onReset = {
                                backStack.add(RouteOnboardingCollectionDay)
                            }
                        )
                    }

                    else -> {
                        CollectionDayScreen(
                            onDayChosen = { day ->
                                viewModel.handle(ScheduleIntent.DayChosen(day))
                                backStack.add(RouteOnboardingLastCollectionType)
                            }
                        )
                    }
                }
            }

            entry<RouteOnboardingCollectionDay> {
                CollectionDayScreen(
                    onDayChosen = { day ->
                        viewModel.handle(ScheduleIntent.DayChosen(day))
                        backStack.add(RouteOnboardingLastCollectionType)
                    }
                )
            }

            entry<RouteOnboardingLastCollectionType> {
                LastCollectionScreen(
                    lastCollectionDay = viewModel.uiState.collectAsState().value.collectionDay,
                    onLastCollectionChosen = { collection ->
                        viewModel.handle(ScheduleIntent.LastBinType(collection))
                        backStack.add(RouteOnboardingRecyclingLidColor)
                    }
                )
            }

            entry<RouteOnboardingRecyclingLidColor> {
                RecyclingLidScreen(
                    onRecyclingLidColorChosen = { color ->
                        viewModel.handle(ScheduleIntent.RecyclingLidColor(color))
                        backStack.add(RouteOnboardingGardenLidColor)
                    }
                )
            }

            entry<RouteOnboardingGardenLidColor> {
                GardenLidScreen(
                    onGardenLidColorChosen = { color ->
                        viewModel.handleFinalOnboardingScreen(ScheduleIntent.GardenLidColor(color))
                        backStack.add(RouteInitialScreen)
                    }
                )
            }

//            entry<RouteHome> { key ->
//                val viewModel = hiltViewModel<HomeScreenViewModel, HomeScreenViewModel.Factory>(
//                    creationCallback = { factory ->
//                        factory.create(key)
//                    }
//                )
//                HomeScreen(
//                    viewModel = viewModel,
//                    onReset = {
//                        backStack.add(RouteOnboardingCollectionDay)
//                    }
//                )
//            }
        }
    )
}
