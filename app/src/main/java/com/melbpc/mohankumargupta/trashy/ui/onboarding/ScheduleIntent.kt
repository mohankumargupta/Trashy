package com.melbpc.mohankumargupta.trashy.ui.onboarding

import androidx.compose.ui.graphics.Color
import com.melbpc.mohankumargupta.trashy.data.model.BinType

sealed interface ScheduleIntent {
    data class DayChosen(val day: String) : ScheduleIntent
    data class LastBinType(val type: BinType) : ScheduleIntent
    data class RecyclingLidColor(val color: Color) : ScheduleIntent
    data class GardenLidColor(val color: Color) : ScheduleIntent
}