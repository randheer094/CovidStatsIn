package me.randheer.covidstatsin.domain.model

import kotlinx.serialization.Serializable
import me.randheer.covidstatsin.db.CovidDistrictStats
import me.randheer.covidstatsin.domain.abstraction.Mapper
import me.randheer.covidstatsin.utils.toDisplayFormat

@Serializable
class DistrictUiModel(
    val stateCode: String,
    val name: String,
    val confirmed: String,
    val deceased: String,
    val recovered: String,
    val tested: String,
    val vaccinated1: String,
    val vaccinated2: String
) {
    val confirmedTitle: String = "Confirmed"
    val deceasedTitle: String = "Deceased"
    val recoveredTitle: String = "Recovered"
    val testedTitle: String = "Tested"
    val vaccinated1Title: String = "Partially Vaccinated"
    val vaccinated2Title: String = "Fully Vaccinated"
}

class DistrictUiModelMapper : Mapper<CovidDistrictStats, DistrictUiModel> {
    override fun map(input: CovidDistrictStats): DistrictUiModel {
        return DistrictUiModel(
            stateCode = input.stateCode,
            name = input.name,
            confirmed = input.confirmed.toDisplayFormat(),
            deceased = input.deceased.toDisplayFormat(),
            recovered = input.recovered.toDisplayFormat(),
            tested = input.tested.toDisplayFormat(),
            vaccinated1 = input.vaccinated1.toDisplayFormat(),
            vaccinated2 = input.vaccinated2.toDisplayFormat()
        )
    }

}