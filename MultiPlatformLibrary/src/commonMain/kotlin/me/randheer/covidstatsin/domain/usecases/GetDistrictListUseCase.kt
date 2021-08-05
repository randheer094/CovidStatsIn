package me.randheer.covidstatsin.domain.usecases

import me.randheer.covidstatsin.domain.abstraction.CoroutineUseCase
import me.randheer.covidstatsin.domain.model.DistrictUiModel
import me.randheer.covidstatsin.domain.model.DistrictUiModelMapper
import me.randheer.covidstatsin.domain.repo.DistrictRepository

class GetDistrictListUseCase(
    private val repository: DistrictRepository,
    private val mapper: DistrictUiModelMapper
) : CoroutineUseCase<GetDistrictListUseCase.Param, List<DistrictUiModel>> {

    override suspend fun run(input: Param): List<DistrictUiModel> {
        return repository.getDistrict(input.stateCode, input.query).map { mapper.map(it) }
    }

    class Param(val stateCode: String, val query: String)
}