package me.randheer.covidstatsin.android.util

import androidx.compose.ui.graphics.Color

object ColorUtil {
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor(colorString))
    }
}