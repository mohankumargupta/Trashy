package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.annotation.DrawableRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.melbpc.mohankumargupta.trashy.R
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import com.melbpc.mohankumargupta.trashy.data.repository.SettingsRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
//import com.melbpc.mohankumargupta.trashy.ui.navigation.RouteHome
//import dagger.assisted.Assisted
//import dagger.assisted.AssistedFactory
//import dagger.assisted.AssistedInject
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val settingsRepository: SettingsRepositoryInterface
) : ViewModel() {
    val collection = settingsRepository.load().map { collectionInfo ->
        val nextCollectionInfo = collectionInfo.nextBinRecycling()
        val nextBin = if (nextCollectionInfo) BinType.RECYCLING else BinType.GARDEN
        val nextLidColor =
            if (nextBin == BinType.RECYCLING) collectionInfo.recyclingLidColor else collectionInfo.gardenLidColor
        binDrawable(nextBin, nextLidColor)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    @DrawableRes
    private fun binDrawable(bin: BinType, lidColor: ColorSwatch): Int? = when (bin to lidColor) {
        BinType.RECYCLING to ColorSwatch.Black -> R.drawable.recycling_bin_black
        BinType.RECYCLING to ColorSwatch.DarkGreen -> R.drawable.recycling_bin_darkgreen
        BinType.RECYCLING to ColorSwatch.Green -> R.drawable.recycling_bin_green
        BinType.RECYCLING to ColorSwatch.Grey -> R.drawable.recycling_bin_grey
        BinType.RECYCLING to ColorSwatch.Red -> R.drawable.recycling_bin_red
        BinType.RECYCLING to ColorSwatch.Yellow -> R.drawable.recycling_bin_yellow
        BinType.RECYCLING to ColorSwatch.Blue -> R.drawable.recycling_bin_blue
        BinType.RECYCLING to ColorSwatch.Purple -> R.drawable.recycling_bin_purple

        BinType.GARDEN to ColorSwatch.Black -> R.drawable.garden_bin_black
        BinType.GARDEN to ColorSwatch.DarkGreen -> R.drawable.garden_bin_darkgreen
        BinType.GARDEN to ColorSwatch.Green -> R.drawable.garden_bin_green
        BinType.GARDEN to ColorSwatch.Grey -> R.drawable.garden_bin_grey
        BinType.GARDEN to ColorSwatch.Red -> R.drawable.garden_bin_red
        BinType.GARDEN to ColorSwatch.Yellow -> R.drawable.garden_bin_yellow
        BinType.GARDEN to ColorSwatch.Blue -> R.drawable.garden_bin_blue
        BinType.GARDEN to ColorSwatch.Purple -> R.drawable.garden_bin_purple
        else -> null
    }

    fun resetSettings() {
        viewModelScope.launch {
            settingsRepository.reset()
        }
    }
}

