package me.randheer.covidstatsin.data.mapper

import me.randheer.covidstatsin.data.model.CovidApiData
import me.randheer.covidstatsin.data.model.DistrictApiModel
import me.randheer.covidstatsin.data.model.StateApiModel
import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.db.CovidStateStats
import me.randheer.covidstatsin.domain.abstraction.Mapper

internal typealias CovidLocalData = Pair<List<CovidStateStats>, List<CovidDistrictStats>>

class ApiEntityMapper : Mapper<CovidApiData, CovidLocalData> {

    private val stateNames = mapOf(
        "AN" to "Andaman and Nicobar Islands",
        "AP" to "Andhra Pradesh",
        "AR" to "Arunachal Pradesh",
        "AS" to "Assam",
        "BR" to "Bihar",
        "CH" to "Chandigarh",
        "CT" to "Chhattisgarh",
        "DL" to "Delhi",
        "DN" to "Dadra and Nagar Haveli",
        "GA" to "Goa",
        "GJ" to "Gujarat",
        "HP" to "Himachal Pradesh",
        "HR" to "Haryana",
        "JH" to "Jharkhand",
        "JK" to "Jammu and Kashmir",
        "KA" to "Karnataka",
        "KL" to "Kerala",
        "LA" to "Ladakh",
        "LD" to "Lakshadweep",
        "MH" to "Maharashtra",
        "ML" to "Meghalaya",
        "MN" to "Manipur",
        "MP" to "Madhya Pradesh",
        "MZ" to "Mizoram",
        "NL" to "Nagaland",
        "OR" to "Odisha",
        "PB" to "Punjab",
        "PY" to "Puducherry",
        "RJ" to "Rajasthan",
        "SK" to "Sikkim",
        "TG" to "Telangana",
        "TN" to "Tamil Nadu",
        "TR" to "Tripura",
        "TT" to "Total",
        "UP" to "Uttar Pradesh",
        "UT" to "Uttarakhand",
        "WB" to "West Bengal"
    )

    override fun map(input: CovidApiData): CovidLocalData {
        val states = mutableListOf<CovidStateStats>()
        val districts = mutableListOf<CovidDistrictStats>()
        input.entries.map {
            states.add(toStateData(it.key, it.value))
            districts.addAll(toDistrictData(it.key, it.value.districts.orEmpty()))
        }
        val sortedStates = states.sortedWith(Comparator { o1, o2 ->
            return@Comparator when {
                o1.code == "TT" -> 1
                o2.code == "TT" -> -1
                else -> o1.code.compareTo(o2.code)
            }
        })
        return Pair(sortedStates, districts)
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