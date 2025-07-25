package com.melbpc.mohankumargupta.trashy.data.model

import androidx.compose.ui.graphics.Color

enum class ColorSwatch {
    Black,
    DarkGreen,
    Green,
    Grey,
    Red,
    Yellow,
    Blue,
    Purple;

    fun toColor(): Color {
        return when (this) {
            Black -> Color(0xFF000000)
            DarkGreen -> Color(0xFF1D6E2A)
            Green -> Color(0xFF5FCC6A)
            Grey -> Color(0xFF8C8C8C)
            Red -> Color(0xFFF90101)
            Yellow -> Color(0xFFFFE600)
            Blue -> Color(0xFF00A3FF)
            Purple -> Color(0xFFB901F9)
        }
    }
}