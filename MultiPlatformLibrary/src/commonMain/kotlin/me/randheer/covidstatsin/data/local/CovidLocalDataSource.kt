package me.randheer.covidstatsin.data.local

import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.db.CovidStateStats

class CovidLocalDataSource {

    fun save(entityData: Pair<List<CovidStateStats>, List<CovidDistrictStats>>) {
        Db.getDb().covidStateStatsQueries.deleteAll()
        Db.getDb().covidDistrictStatsQueries.deleteAll()
        entityData.first.forEach { Db.getDb().covidStateStatsQueries.insertNew(it) }
        entityData.second.forEach { Db.getDb().covidDistrictStatsQueries.insertNew(it) }
    }

    fun getCovidStateData(stateCode: String): List<CovidDistrictStats> {
        return Db.getDb().covidDistrictStatsQueries.getByStateCode(stateCode).executeAsList()
    }
}