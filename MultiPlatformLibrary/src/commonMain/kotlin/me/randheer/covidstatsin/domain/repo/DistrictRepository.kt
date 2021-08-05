package me.randheer.covidstatsin.domain.repo

import me.randheer.covidstatsin.db.CovidDistrictStats

interface DistrictRepository {
    suspend fun getDistrict(stateCode: String, query: String): List<CovidDistrictStats>
}