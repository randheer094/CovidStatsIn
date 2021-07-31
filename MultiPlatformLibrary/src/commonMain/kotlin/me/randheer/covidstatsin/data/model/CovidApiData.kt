package me.randheer.covidstatsin.data.model

import kotlinx.serialization.Serializable

internal typealias CovidApiData = Map<String, StateApiModel>
internal typealias CovidStateApiData = Map<String, DistrictApiModel>

@Serializable
data class StateApiModel(
    val districts: CovidStateApiData? = null,
    val meta: MetaApiModel? = null,
    val total: TotalApiModel? = null
)

@Serializable
data class DistrictApiModel(
    val meta: MetaApiModel? = null,
    val total: TotalApiModel? = null
)

@Serializable
data class MetaApiModel(
    val date: String? = null,
    val last_updated: String? = null,
    val population: Long? = null,
    val tested: TestedApiModel? = null,
    val vaccinated: VaccinatedApiModel? = null
)

@Serializable
data class TestedApiModel(
    val date: String? = null
)

@Serializable
data class VaccinatedApiModel(
    val date: String? = null
)

@Serializable
data class TotalApiModel(
    val confirmed: Long? = null,
    val deceased: Long? = null,
    val recovered: Long? = null,
    val tested: Long? = null,
    val vaccinated1: Long? = null,
    val vaccinated2: Long? = null
)