package me.randheer.covidstatsin.domain.repo

import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.db.CovidStateStats

interface CovidRepository {
    suspend fun getCovidData(): List<CovidStateStats>
    suspend fun getCovidStateData(stateCode: String): List<CovidDistrictStats>
}