package me.randheer.covidstatsin.domain.repo

import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.db.CovidStateStats

interface CovidRepository {
    suspend fun getStates(): List<CovidStateStats>
    suspend fun searchStates(query: String): List<CovidStateStats>
    suspend fun getDistricts(stateCode: String): List<CovidDistrictStats>
    suspend fun searchDistrict(stateCode: String, query: String): List<CovidDistrictStats>
}