package me.randheer.covidstatsin.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.randheer.covidstatsin.domain.abstraction.CoroutineUseCase
import me.randheer.covidstatsin.domain.model.DistrictUiModel
import me.randheer.covidstatsin.domain.model.DistrictUiModelMapper
import me.randheer.covidstatsin.domain.repo.DistrictRepository

class GetDistrictListUseCase(
    private val repository: DistrictRepository,
    private val mapper: DistrictUiModelMapper
) : CoroutineUseCase<GetDistrictListUseCase.Param, List<DistrictUiModel>> {

    @Throws(Exception::class)
    override suspend fun run(input: Param): List<DistrictUiModel> {
        val result = withContext(Dispatchers.Default) {
            repository.getDistrict(input.stateCode, input.query).map { mapper.map(it) }
        }
        return withContext(Dispatchers.Main) {
            result
        }
    }

    class Param(val stateCode: String, val query: String)
}