package me.randheer.covidstatsin.data.repo

import me.randheer.covidstatsin.data.local.DistrictLocalDataSource
import me.randheer.covidstatsin.data.local.Prefs
import me.randheer.covidstatsin.data.local.StateLocalDataSource
import me.randheer.covidstatsin.data.mapper.ApiEntityMapper
import me.randheer.covidstatsin.data.remote.CovidRemoteDataSource
import me.randheer.covidstatsin.db.CovidStateStats
import me.randheer.covidstatsin.domain.repo.StateRepository
import me.randheer.covidstatsin.utils.getTodayDayOfYear

class StateRepositoryImpl(
    private val remoteDataSource: CovidRemoteDataSource,
    private val localDataSource: StateLocalDataSource,
    private val districtLocalDataSource: DistrictLocalDataSource,
    private val apiEntityMapper: ApiEntityMapper
) : StateRepository {
    private val lastUpdatedAtKey = "latUpdatedAtKey-loadData"

    override suspend fun getStates(query: String): List<CovidStateStats> {
        val expired = Prefs.getRef().int(lastUpdatedAtKey) != getTodayDayOfYear()
        if (localDataSource.isEmpty() || expired) {
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
        Prefs.getRef().set(lastUpdatedAtKey, getTodayDayOfYear())
    }

    private fun getStates(): List<CovidStateStats> {
        return localDataSource.getAll()
    }

    private fun searchState(searchText: String): List<CovidStateStats> {
        return localDataSource.searchStates(searchText)
    }
}