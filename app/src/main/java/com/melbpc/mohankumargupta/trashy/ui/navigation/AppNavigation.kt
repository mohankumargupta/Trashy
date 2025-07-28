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
import com.melbpc.mohankumargupta.trashy.ui.onboarding.CollectionDayScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.GardenLidScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.LastCollectionScreen
import com.melbpc.mohankumargupta.trashy.ui.onboarding.RecyclingLidScreen

@Composable
fun AppNavigation(
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
                val initialRoute by navViewModel.isOnboardingRequired.collectAsStateWithLifecycle(
                    false
                )
                if (initialRoute) {
                    HomeScreen(
                        onReset = {
                            backStack.add(RouteOnboardingCollectionDay)
                        }
                    )
                } else {
                    CollectionDayScreen(
                        onDayChosen = {
                            backStack.add(RouteOnboardingLastCollectionType)
                        }
                    )
                }
            }

            entry<RouteOnboardingCollectionDay> {
                CollectionDayScreen(
                    onDayChosen = {
                        backStack.add(RouteOnboardingLastCollectionType)
                    }
                )
            }

            entry<RouteOnboardingLastCollectionType> {
                LastCollectionScreen(
                    onLastCollectionChosen = {
                        backStack.add(RouteOnboardingRecyclingLidColor)
                    }
                )
            }

            entry<RouteOnboardingRecyclingLidColor> {
                RecyclingLidScreen(
                    onRecyclingLidColorChosen = {
                        backStack.add(RouteOnboardingGardenLidColor)
                    }
                )
            }

            entry<RouteOnboardingGardenLidColor> {
                GardenLidScreen(
                    onGardenLidColorChosen = {
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
