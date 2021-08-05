package me.randheer.covidstatsin.domain.model

import kotlinx.serialization.Serializable
import me.randheer.covidstatsin.db.CovidStateStats
import me.randheer.covidstatsin.domain.abstraction.Mapper
import me.randheer.covidstatsin.utils.toDisplayFormat
import me.randheer.covidstatsin.utils.toUpdatedDateDisplay

@Serializable
class StateUiModel(
    val code: String,
    val name: String,
    val confirmed: String,
    val deceased: String,
    val recovered: String,
    val tested: String,
    val vaccinated1: String,
    val vaccinated2: String,
    val updatedAt: String
) {
    val confirmedTitle: String = "Confirmed"
    val deceasedTitle: String = "Deceased"
    val recoveredTitle: String = "Recovered"
    val testedTitle: String = "Tested"
    val vaccinated1Title: String = "Partially Vaccinated"
    val vaccinated2Title: String = "Fully Vaccinated"

    val clickable: Boolean
        get() = code != "TT"

    val cardBgColor
        get() = if (clickable) "#FFFFFF" else "#CCCCCC"
}

class StateUiModelMapper : Mapper<CovidStateStats, StateUiModel> {
    override fun map(input: CovidStateStats): StateUiModel {
        return StateUiModel(
            code = input.code,
            name = input.name,
            confirmed = input.confirmed.toDisplayFormat(),
            deceased = input.deceased.toDisplayFormat(),
            recovered = input.recovered.toDisplayFormat(),
            tested = input.tested.toDisplayFormat(),
            vaccinated1 = input.vaccinated1.toDisplayFormat(),
            vaccinated2 = input.vaccinated2.toDisplayFormat(),
            updatedAt = input.updatedAt.toUpdatedDateDisplay()
        )
    }

}