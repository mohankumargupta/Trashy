package com.melbpc.mohankumargupta.trashy.ui.navigation

import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import kotlinx.serialization.Serializable

sealed interface NavigationRoute

@Serializable
data object RouteOnboardingCollectionDay : NavigationRoute

@Serializable
data object RouteOnboardingLastCollectionType :  NavigationRoute

@Serializable
data object RouteOnboardingRecyclingLidColor : NavigationRoute

@Serializable
data object RouteOnboardingGardenLidColor : NavigationRoute

@Serializable
data class RouteHome(val binType: BinType, val color: ColorSwatch) : NavigationRoute

