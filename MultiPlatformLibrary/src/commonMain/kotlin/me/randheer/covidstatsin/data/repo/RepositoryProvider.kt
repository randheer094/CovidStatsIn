package me.randheer.covidstatsin.data.repo

import me.randheer.covidstatsin.data.local.CovidLocalDataSource
import me.randheer.covidstatsin.data.mapper.ApiEntityMapper
import me.randheer.covidstatsin.data.remote.CovidRemoteDataSource

fun getCovidRepository(): CovidRepositoryImpl {
    return CovidRepositoryImpl(
        CovidRemoteDataSource(),
        CovidLocalDataSource(),
        ApiEntityMapper()
    )
}