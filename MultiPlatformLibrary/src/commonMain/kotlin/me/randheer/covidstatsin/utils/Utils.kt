package me.randheer.covidstatsin.utils

fun Long?.toDisplayFormat(): String {
    return if (this == null || this == 0L) "Not Available" else this.toString()
}

fun String?.toUpdatedDateDisplay(): String {
    return "Last Updated at: $this"
}