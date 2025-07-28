package com.melbpc.mohankumargupta.trashy.ui.home

import androidx.lifecycle.ViewModel
import com.melbpc.mohankumargupta.trashy.R
import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch
import com.melbpc.mohankumargupta.trashy.ui.navigation.RouteHome
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel(assistedFactory = HomeScreenViewModel.Factory::class)
class HomeScreenViewModel @AssistedInject constructor(
  @Assisted val navKey: RouteHome
) : ViewModel() {

    val bin = binDrawable(navKey.binType, navKey.color)

    private fun binDrawable(bin: BinType, swatch: ColorSwatch): Int = when (bin to swatch) {
        BinType.RECYCLING to ColorSwatch.Black     -> R.drawable.recycling_bin_black
        BinType.RECYCLING to ColorSwatch.DarkGreen -> R.drawable.recycling_bin_darkgreen
        BinType.RECYCLING to ColorSwatch.Green     -> R.drawable.recycling_bin_green
        BinType.RECYCLING to ColorSwatch.Grey      -> R.drawable.recycling_bin_grey
        BinType.RECYCLING to ColorSwatch.Red       -> R.drawable.recycling_bin_red
        BinType.RECYCLING to ColorSwatch.Yellow    -> R.drawable.recycling_bin_yellow
        BinType.RECYCLING to ColorSwatch.Blue      -> R.drawable.recycling_bin_blue
        BinType.RECYCLING to ColorSwatch.Purple    -> R.drawable.recycling_bin_purple

        BinType.GARDEN    to ColorSwatch.Black     -> R.drawable.garden_bin_black
        BinType.GARDEN    to ColorSwatch.DarkGreen -> R.drawable.garden_bin_darkgreen
        BinType.GARDEN    to ColorSwatch.Green     -> R.drawable.garden_bin_green
        BinType.GARDEN    to ColorSwatch.Grey      -> R.drawable.garden_bin_grey
        BinType.GARDEN    to ColorSwatch.Red       -> R.drawable.garden_bin_red
        BinType.GARDEN    to ColorSwatch.Yellow    -> R.drawable.garden_bin_yellow
        BinType.GARDEN    to ColorSwatch.Blue      -> R.drawable.garden_bin_blue
        BinType.GARDEN    to ColorSwatch.Purple    -> R.drawable.garden_bin_purple
        else -> R.drawable.recycling_bin_black
    }

    @AssistedFactory
    interface Factory {
        fun create(navKey: RouteHome): HomeScreenViewModel
    }

    fun resetSettings() {

    }

}

