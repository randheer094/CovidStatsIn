package me.randheer.covidstatsin.data.local

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import me.randheer.covidstatsin.CovidStatsSharedApplication
import me.randheer.covidstatsin.db.CovidStats

actual fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(
        CovidStats.Schema,
        CovidStatsSharedApplication.appCtx,
        "CovidStats.db"
    )
}