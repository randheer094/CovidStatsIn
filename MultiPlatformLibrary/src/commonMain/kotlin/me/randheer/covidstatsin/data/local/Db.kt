package me.randheer.covidstatsin.data.local

import com.squareup.sqldelight.db.SqlDriver
import me.randheer.covidstatsin.db.CovidStats

expect fun createDriver(): SqlDriver


object Db {
    private var dbRef: CovidStats? = null

    private fun dbSetup(driver: SqlDriver) {
        val db = CovidStats(driver)
        dbRef = db
    }

    fun getDb(): CovidStats {
        if (dbRef == null) dbSetup(createDriver())
        return dbRef!!
    }
}