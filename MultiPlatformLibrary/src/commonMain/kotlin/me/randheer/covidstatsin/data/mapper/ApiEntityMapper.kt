package me.randheer.covidstatsin.data.mapper

import me.randheer.covidstatsin.data.model.CovidApiData
import me.randheer.covidstatsin.data.model.DistrictApiModel
import me.randheer.covidstatsin.data.model.StateApiModel
import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.db.CovidStateStats
import me.randheer.covidstatsin.domain.abstraction.Mapper

internal typealias CovidLocalData = Pair<List<CovidStateStats>, List<CovidDistrictStats>>

class ApiEntityMapper : Mapper<CovidApiData, CovidLocalData> {
    private val stateNames = mapOf<String, String>()

    override fun map(input: CovidApiData): CovidLocalData {
        val states = mutableListOf<CovidStateStats>()
        val districts = mutableListOf<CovidDistrictStats>()
        input.entries.map {
            states.add(toStateData(it.key, it.value))
            districts.addAll(toDistrictData(it.key, it.value.districts.orEmpty()))
        }
        return Pair(states, districts)
    }

    private fun toStateData(
        code: String,
        stateModel: StateApiModel
    ): CovidStateStats {
        return CovidStateStats(
            code = code,
            name = stateNames[code].orEmpty(),
            confirmed = stateModel.total?.confirmed ?: 0,
            deceased = stateModel.total?.deceased ?: 0,
            recovered = stateModel.total?.recovered ?: 0,
            tested = stateModel.total?.tested ?: 0,
            vaccinated1 = stateModel.total?.vaccinated1 ?: 0,
            vaccinated2 = stateModel.total?.vaccinated2 ?: 0,
            population = stateModel.meta?.population ?: 0,
            vaccinationUpdatedAt = stateModel.meta?.vaccinated?.date.orEmpty(),
            testedUpdatedAt = stateModel.meta?.tested?.date.orEmpty(),
            updatedAt = stateModel.meta?.last_updated.orEmpty(),
        )
    }

    private fun toDistrictData(
        code: String,
        districtModel: Map<String, DistrictApiModel>
    ): List<CovidDistrictStats> {
        return districtModel.entries.map { d ->
            CovidDistrictStats(
                stateCode = code,
                name = d.key,
                confirmed = d.value.total?.confirmed ?: 0,
                deceased = d.value.total?.deceased ?: 0,
                recovered = d.value.total?.recovered ?: 0,
                tested = d.value.total?.tested ?: 0,
                vaccinated1 = d.value.total?.vaccinated1 ?: 0,
                vaccinated2 = d.value.total?.vaccinated2 ?: 0,
                population = d.value.meta?.population ?: 0,
            )
        }
    }
}