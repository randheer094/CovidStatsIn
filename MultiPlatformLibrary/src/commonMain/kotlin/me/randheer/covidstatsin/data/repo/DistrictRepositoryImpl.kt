package me.randheer.covidstatsin.data.repo

import me.randheer.covidstatsin.data.local.DistrictLocalDataSource
import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.domain.repo.DistrictRepository

class DistrictRepositoryImpl(
    private val localDataSource: DistrictLocalDataSource,
) : DistrictRepository {

    override suspend fun getDistrict(stateCode: String, query: String): List<CovidDistrictStats> {
        return if (query.isEmpty()) getDistricts(stateCode)
        else searchDistrictInternal(stateCode, query)
    }

    private fun getDistricts(stateCode: String): List<CovidDistrictStats> {
        return localDataSource.getByStateCode(stateCode)
    }

    private fun searchDistrictInternal(stateCode: String, query: String): List<CovidDistrictStats> {
        return localDataSource.searchDistricts(stateCode, query)
    }
}