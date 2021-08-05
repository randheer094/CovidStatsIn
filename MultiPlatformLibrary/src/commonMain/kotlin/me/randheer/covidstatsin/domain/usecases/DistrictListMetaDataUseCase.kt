package me.randheer.covidstatsin.domain.usecases

import me.randheer.covidstatsin.domain.abstraction.BasicUseCase
import me.randheer.covidstatsin.domain.model.DistrictListMetaData

class DistrictListMetaDataUseCase :
    BasicUseCase<DistrictListMetaDataUseCase.Param, DistrictListMetaData> {

    override fun run(input: Param): DistrictListMetaData {
        return DistrictListMetaData(
            title = "District list (${input.stateCode})",
            searchPlaceholder = "Search by district name"
        )
    }

    class Param(val stateCode: String)
}