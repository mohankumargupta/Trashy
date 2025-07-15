package com.melbpc.mohankumargupta.trashy.data.model

import androidx.compose.ui.graphics.Color
import java.time.LocalDate

data class CollectionInfo (
    val collectionDay: String = "Monday",
    val infoDate: LocalDate = LocalDate.MIN,
    val lastCollectionBinType: BinType = BinType.RECYCLING,
    val recyclingLidColor: Color = Color.Transparent,
    val gardenLidColor: Color = Color.Transparent,
) {
// define nextCollectionDate here
}