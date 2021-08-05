package me.randheer.covidstatsin.data.local

import me.randheer.covidstatsin.db.CovidDistrictStats

class DistrictLocalDataSource {

    fun save(data: List<CovidDistrictStats>) {
        Db.getDb().covidDistrictStatsQueries.deleteAll()
        data.forEach { Db.getDb().covidDistrictStatsQueries.insertNew(it) }
    }

    fun getByStateCode(stateCode: String): List<CovidDistrictStats> {
        return Db.getDb().covidDistrictStatsQueries.getByStateCode(stateCode).executeAsList()
    }

    fun searchDistricts(stateCode: String, query: String): List<CovidDistrictStats> {
        val searchQuery = "%$query%"
        return Db.getDb().covidDistrictStatsQueries.searchDistrict(stateCode, searchQuery)
            .executeAsList()
    }
}