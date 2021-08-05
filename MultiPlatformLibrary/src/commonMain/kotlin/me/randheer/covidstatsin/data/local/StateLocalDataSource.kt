package me.randheer.covidstatsin.data.local

import me.randheer.covidstatsin.db.CovidStateStats

class StateLocalDataSource {

    fun isEmpty(): Boolean {
        return Db.getDb().covidStateStatsQueries.getItemCount().executeAsOne() == 0L
    }

    fun save(data: List<CovidStateStats>) {
        Db.getDb().covidStateStatsQueries.deleteAll()
        data.forEach { Db.getDb().covidStateStatsQueries.insertNew(it) }
    }

    fun getAll(): List<CovidStateStats> {
        return Db.getDb().covidStateStatsQueries.getAll().executeAsList()
    }

    fun searchStates(query: String): List<CovidStateStats> {
        val searchQuery = "%$query%"
        return Db.getDb().covidStateStatsQueries.searchByQuery(searchQuery, searchQuery)
            .executeAsList()
    }
}