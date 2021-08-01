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

    override suspend fun getCovidData(): List<CovidStateStats> {
        val data = remoteDataSource.getCovidDate()
        val entityData = apiEntityMapper.map(data)
        localDataSource.save(entityData)
        return entityData.first
    }

    override suspend fun getCovidStateData(stateCode: String): List<CovidDistrictStats> {
        return localDataSource.getCovidStateData(stateCode)
    }
}