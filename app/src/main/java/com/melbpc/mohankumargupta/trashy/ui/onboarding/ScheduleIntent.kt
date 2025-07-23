package com.melbpc.mohankumargupta.trashy.ui.onboarding

import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.ColorSwatch

sealed interface ScheduleIntent {
    data class DayChosen(val day: String) : ScheduleIntent
    data class LastBinType(val type: BinType) : ScheduleIntent
    data class RecyclingLidColor(val color: ColorSwatch) : ScheduleIntent
    data class GardenLidColor(val color: ColorSwatch) : ScheduleIntent
}