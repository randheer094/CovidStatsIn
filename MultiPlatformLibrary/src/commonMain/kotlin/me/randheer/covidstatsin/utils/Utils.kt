package me.randheer.covidstatsin.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.math.abs

fun Long?.toDisplayFormat(): String {
    return if (this == null || this == 0L) "Not Available" else formatNumberIn(this)
}

fun String?.toUpdatedDateDisplay(): String {
    return "Last Updated at: $this"
}

fun formatNumberIn(num: Long): String {
    val raw = abs(num).toString()
    val numDigits = raw.length
    var sb = StringBuilder(raw)
    sb = sb.reverse()
    var commas = 0
    for (i in 0 until numDigits) {
        if (i % 2 == 1 && i != 1) {
            sb.insert(i + commas, ",")
            commas++
        }
    }
    val sign = if (num < 0) "-" else ""
    return sign + sb.reverse().toString()
}

fun getTodayDayOfYear() =
    Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).dayOfYear