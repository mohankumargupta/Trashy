package com.melbpc.mohankumargupta.trashy.ui.navigation

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

sealed interface NavigationRoute

@Serializable
data object OnboardingCollectionDay : NavigationRoute

@Serializable
data object OnboardingLastCollectionType :  NavigationRoute

@Serializable
data object OnboardingRecyclingLidColor : NavigationRoute

@Serializable
data object OnboardingGardenLidColor : NavigationRoute

@Serializable
data class Home(@param:DrawableRes val binDrawable: Int) : NavigationRoute

