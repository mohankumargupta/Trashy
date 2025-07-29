package com.melbpc.mohankumargupta.trashy.data.model

import androidx.annotation.VisibleForTesting
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

data class CollectionInfo (
    val collectionDay: DayOfWeek = DayOfWeek.MONDAY,
    val infoDate: LocalDate = LocalDate.MIN,
    val lastCollectionBinType: BinType = BinType.RECYCLING,
    val recyclingLidColor: ColorSwatch = ColorSwatch.Black,
    val gardenLidColor: ColorSwatch = ColorSwatch.Black,
) {
    @VisibleForTesting
    internal fun getReferenceRecyclingDate(): LocalDate {
        //val day = DayOfWeek.valueOf(collectionDay.uppercase())
        val lastCollectionDate = infoDate.with(TemporalAdjusters.previousOrSame(collectionDay))
        val weeksBack = if (lastCollectionBinType == BinType.GARDEN) 1L else 0L
        return lastCollectionDate.minusWeeks(weeksBack)
    }

    fun nextBinRecycling(date: LocalDate = LocalDate.now()): Boolean {
        val nextCollectionDate =
            if (date.dayOfWeek == collectionDay) date
            else date.with(TemporalAdjusters.next(collectionDay))

        val weeksDelta = java.time.temporal.ChronoUnit.WEEKS.between(
            getReferenceRecyclingDate(),
            nextCollectionDate
        )

        return weeksDelta % 2 == 0L
    }
}
