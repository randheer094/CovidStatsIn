package me.randheer.covidstatsin.data.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.Atomic
import me.randheer.covidstatsin.db.CovidStats

expect fun createDriver(): SqlDriver

object Db {
    private var dbRef: Atomic<CovidStats?> = Atomic(null)

    private fun dbSetup(driver: SqlDriver) {
        dbRef.set(CovidStats(driver))
    }

    fun getDb(): CovidStats {
        if (dbRef.get() == null) dbSetup(createDriver())
        return dbRef.get()!!
    }
}