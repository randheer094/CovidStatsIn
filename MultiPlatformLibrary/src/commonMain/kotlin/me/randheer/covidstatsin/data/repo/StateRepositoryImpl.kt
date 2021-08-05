package me.randheer.covidstatsin.data.repo

import me.randheer.covidstatsin.data.local.DistrictLocalDataSource
import me.randheer.covidstatsin.data.local.StateLocalDataSource
import me.randheer.covidstatsin.data.mapper.ApiEntityMapper
import me.randheer.covidstatsin.data.remote.CovidRemoteDataSource
import me.randheer.covidstatsin.db.CovidStateStats
import me.randheer.covidstatsin.domain.repo.StateRepository

class StateRepositoryImpl(
    private val remoteDataSource: CovidRemoteDataSource,
    private val localDataSource: StateLocalDataSource,
    private val districtLocalDataSource: DistrictLocalDataSource,
    private val apiEntityMapper: ApiEntityMapper
) : StateRepository {

    override suspend fun getStates(query: String): List<CovidStateStats> {
        if (localDataSource.isEmpty()) {
            loadData()
        }
        return if (query.isEmpty()) getStates()
        else searchState(query)
    }

    private suspend fun loadData() {
        val data = remoteDataSource.getCovidDate()
        val entityData = apiEntityMapper.map(data)
        localDataSource.save(entityData.first)
        districtLocalDataSource.save(entityData.second)
    }

    private fun getStates(): List<CovidStateStats> {
        return localDataSource.getAll()
    }

    private fun searchState(searchText: String): List<CovidStateStats> {
        return localDataSource.searchStates(searchText)
    }
}