package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface NavigationRoute : NavKey

@Serializable
data object RouteInitialScreen: NavigationRoute

@Serializable
data object RouteOnboardingCollectionDay : NavigationRoute

@Serializable
data object RouteOnboardingLastCollectionType :  NavigationRoute

@Serializable
data object RouteOnboardingRecyclingLidColor : NavigationRoute

@Serializable
data object RouteOnboardingGardenLidColor : NavigationRoute
