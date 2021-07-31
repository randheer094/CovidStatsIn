package me.randheer.covidstatsin.data.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import me.randheer.covidstatsin.db.CovidStats

actual fun createDriver(): SqlDriver {
    return NativeSqliteDriver(CovidStats.Schema, "CovidStats.db")
}