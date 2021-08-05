package me.randheer.covidstatsin.domain.repo

import me.randheer.covidstatsin.db.CovidStateStats

interface StateRepository {
    suspend fun getStates(query: String): List<CovidStateStats>
}