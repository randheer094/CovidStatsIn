package me.randheer.covidstatsin.domain.usecases

import me.randheer.covidstatsin.domain.abstraction.BasicUseCase
import me.randheer.covidstatsin.domain.model.StateListMetaData

class StateListMetaDataUseCase : BasicUseCase<Unit, StateListMetaData> {
    override fun run(input: Unit): StateListMetaData {
        return StateListMetaData(
            title = "State list (India)",
            searchPlaceholder = "Search by state name or code"
        )
    }
}