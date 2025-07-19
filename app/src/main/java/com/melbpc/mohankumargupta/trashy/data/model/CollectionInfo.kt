package com.melbpc.mohankumargupta.trashy.data.model

import androidx.annotation.VisibleForTesting
import androidx.compose.ui.graphics.Color
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class CollectionInfo (
    val collectionDay: String = "Monday",
    val infoDate: LocalDate = LocalDate.MIN,
    val lastCollectionBinType: BinType = BinType.RECYCLING,
    val recyclingLidColor: Color = Color.Transparent,
    val gardenLidColor: Color = Color.Transparent,
) {


    @VisibleForTesting
    internal fun getReferenceRecyclingDate(): LocalDate {
        val day = DayOfWeek.valueOf(collectionDay.uppercase())
        val lastCollectionDate = infoDate.with(TemporalAdjusters.previousOrSame(day))
        val weeksBack = if (lastCollectionBinType == BinType.GARDEN) 1L else 0L
        return lastCollectionDate.minusWeeks(weeksBack)
    }

    fun nextBin(date: LocalDate): BinType {
        val day = DayOfWeek.valueOf(collectionDay.uppercase())

        // The next collection day on or after the given date
        val nextCollectionDate =
            if (date.dayOfWeek == day) date
            else date.with(TemporalAdjusters.next(day))

        // Number of whole weeks between the reference recycling date and the next collection
        val weeksDelta = java.time.temporal.ChronoUnit.WEEKS.between(
            getReferenceRecyclingDate(),
            nextCollectionDate
        )

        // Even weeks → recycling, odd weeks → garden
        return if (weeksDelta % 2 == 0L) BinType.RECYCLING else BinType.GARDEN
    }




}