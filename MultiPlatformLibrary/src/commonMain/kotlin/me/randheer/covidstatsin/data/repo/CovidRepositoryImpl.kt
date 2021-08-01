package me.randheer.covidstatsin.data.repo

import me.randheer.covidstatsin.data.local.CovidLocalDataSource
import me.randheer.covidstatsin.data.mapper.ApiEntityMapper
import me.randheer.covidstatsin.data.remote.CovidRemoteDataSource
import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.db.CovidStateStats
import me.randheer.covidstatsin.domain.repo.CovidRepository

class CovidRepositoryImpl(
    private val remoteDataSource: CovidRemoteDataSource,
    private val localDataSource: CovidLocalDataSource,
    private val apiEntityMapper: ApiEntityMapper
) : CovidRepository {

    override suspend fun getStates(): List<CovidStateStats> {
        val data = remoteDataSource.getCovidDate()
        val entityData = apiEntityMapper.map(data)
        localDataSource.save(entityData)
        return entityData.first
    }

    override suspend fun searchStates(query: String): List<CovidStateStats> {
        return if (query.isEmpty()) getStates()
        else searchStateInternal(query)
    }

    override suspend fun getDistricts(stateCode: String): List<CovidDistrictStats> {
        return localDataSource.getDistricts(stateCode)
    }

    override suspend fun searchDistrict(
        stateCode: String,
        query: String
    ): List<CovidDistrictStats> {
        return if (query.isEmpty()) getDistricts(stateCode)
        else searchDistrictInternal(stateCode, query)
    }

    private fun searchStateInternal(searchText: String): List<CovidStateStats> {
        return localDataSource.searchStates(searchText)
    }

    private fun searchDistrictInternal(stateCode: String, query: String): List<CovidDistrictStats> {
        return localDataSource.searchDistricts(stateCode, query)
    }
}