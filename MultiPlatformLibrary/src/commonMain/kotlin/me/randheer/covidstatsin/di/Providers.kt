package me.randheer.covidstatsin.di

import me.randheer.covidstatsin.data.local.DistrictLocalDataSource
import me.randheer.covidstatsin.data.local.StateLocalDataSource
import me.randheer.covidstatsin.data.mapper.ApiEntityMapper
import me.randheer.covidstatsin.data.remote.CovidRemoteDataSource
import me.randheer.covidstatsin.data.repo.DistrictRepositoryImpl
import me.randheer.covidstatsin.data.repo.StateRepositoryImpl
import me.randheer.covidstatsin.domain.model.DistrictUiModelMapper
import me.randheer.covidstatsin.domain.model.StateUiModelMapper
import me.randheer.covidstatsin.domain.usecases.DistrictListMetaDataUseCase
import me.randheer.covidstatsin.domain.usecases.GetDistrictListUseCase
import me.randheer.covidstatsin.domain.usecases.GetStateListUseCase
import me.randheer.covidstatsin.domain.usecases.StateListMetaDataUseCase

// TODO Implement one of DI frameworks https://github.com/AAkira/Kotlin-Multiplatform-Libraries#di
object Providers {

    private val remoteDataSource = CovidRemoteDataSource()
    private val stateLocalDataSource = StateLocalDataSource()
    private val districtLocalDataSource = DistrictLocalDataSource()
    private val apiEntityMapper = ApiEntityMapper()

    internal val stateRepository = StateRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = stateLocalDataSource,
        districtLocalDataSource = districtLocalDataSource,
        apiEntityMapper = apiEntityMapper
    )
    internal val stateUiModelMapper = StateUiModelMapper()

    internal val districtRepository = DistrictRepositoryImpl(
        localDataSource = districtLocalDataSource,
    )
    internal val districtUiMapper = DistrictUiModelMapper()
}

val stateUseCase = GetStateListUseCase(Providers.stateRepository, Providers.stateUiModelMapper)
val stateMetaDataUseCase = StateListMetaDataUseCase()
val districtUseCase = GetDistrictListUseCase(
    Providers.districtRepository,
    Providers.districtUiMapper
)
val districtMetaDataUseCase = DistrictListMetaDataUseCase()