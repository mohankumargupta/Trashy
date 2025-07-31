package com.mohankumargupta.trashy.data.model

import com.melbpc.mohankumargupta.trashy.data.model.BinType
import com.melbpc.mohankumargupta.trashy.data.model.CollectionInfo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.DayOfWeek
import java.time.LocalDate

class CollectionInfoTest {
    @Test
    fun `getReferenceRecyclingDate - last collection was recycling`() {
        val infoDate = LocalDate.of(2025, 7, 19)          // Saturday
        val collectionInfo = CollectionInfo(
            infoDate = infoDate,
            lastCollectionBinType = BinType.RECYCLING,
            collectionDay = DayOfWeek.MONDAY
        )

        val result = collectionInfo.getReferenceRecyclingDate() // <--- Call method on instance

        // last Monday on/before 19-07-2025 is 14-07-2025, same week → recycling
        assertEquals(LocalDate.of(2025, 7, 14), result)
    }

    @Test
    fun `getReferenceRecyclingDate - last collection was garden`() {
        val infoDate = LocalDate.of(2025, 7, 19)          // Saturday
        val collectionInfo = CollectionInfo(              // <--- Create instance
            infoDate = infoDate,
            lastCollectionBinType = BinType.GARDEN,
            collectionDay = DayOfWeek.MONDAY
        )

        val result = collectionInfo.getReferenceRecyclingDate() // <--- Call method on instance

        // last Monday is 14-07-2025 (garden) → recycling was 07-07-2025
        assertEquals(LocalDate.of(2025, 7, 7), result)
    }

    @Test
    fun `getReferenceRecyclingDate - collection day is exactly infoDate, last was recycling`() {
        val infoDate = LocalDate.of(2025, 7, 14)          // Monday
        val collectionInfo = CollectionInfo(              // <--- Create instance
            infoDate = infoDate,
            lastCollectionBinType = BinType.RECYCLING,
            collectionDay = DayOfWeek.MONDAY
        )

        val result = collectionInfo.getReferenceRecyclingDate() // <--- Call method on instance

        // infoDate itself is the collection day → no adjustment needed
        assertEquals(LocalDate.of(2025, 7, 14), result)
    }

    @Test
    fun `getReferenceRecyclingDate - collection day is exactly infoDate, last was garden`() {
        val infoDate = LocalDate.of(2025, 7, 14)          // Monday
        val collectionInfo = CollectionInfo(              // <--- Create instance
            infoDate = infoDate,
            lastCollectionBinType = BinType.GARDEN,
            collectionDay = DayOfWeek.MONDAY
        )

        val result = collectionInfo.getReferenceRecyclingDate() // <--- Call method on instance

        // infoDate is collection day (garden), so recycling reference is previous week
        assertEquals(LocalDate.of(2025, 7, 7), result)
    }

    @Test
    fun `nextBin returns RECYCLING when next collection is recycling week`() {
        // A Thursday-based schedule, last collected GARDEN on 2024-05-23
        val info = CollectionInfo(
            collectionDay = DayOfWeek.THURSDAY,
            infoDate = LocalDate.of(2024, 5, 23), // Thursday
            lastCollectionBinType = BinType.GARDEN
        )

        // Ask on the following Monday (2024-05-27) what bin comes next
        val next = info.nextBinRecycling(LocalDate.of(2024, 5, 27))

        assertEquals(true, next)
    }

    @Test
    fun `nextBin returns GARDEN when next collection is garden week`() {
        // A Monday-based schedule, last collected RECYCLING on 2024-06-03
        val info = CollectionInfo(
            collectionDay = DayOfWeek.MONDAY,
            infoDate = LocalDate.of(2024, 6, 3), // Monday
            lastCollectionBinType = BinType.RECYCLING
        )

        // Ask on the same Monday (2024-06-03) what bin comes next
        val next = info.nextBinRecycling(LocalDate.of(2024, 6, 3))

        assertEquals(true, next)
    }

    @Test
    fun `nextBin same day as infoDate returns lastCollectionBinType`() {
        // A Wednesday-based schedule, last collected GARDEN on 2024-07-17
        val info = CollectionInfo(
            collectionDay = DayOfWeek.WEDNESDAY,
            infoDate = LocalDate.of(2024, 7, 17), // Wednesday
            lastCollectionBinType = BinType.GARDEN
        )

        // Ask on the very same day (2024-07-17) what bin comes next
        val next = info.nextBinRecycling(info.infoDate)

        assertEquals(false, next)
    }

}
