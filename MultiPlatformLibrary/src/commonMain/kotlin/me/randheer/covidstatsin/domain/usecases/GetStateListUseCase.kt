package me.randheer.covidstatsin.domain.usecases

import me.randheer.covidstatsin.domain.abstraction.CoroutineUseCase
import me.randheer.covidstatsin.domain.model.StateUiModel
import me.randheer.covidstatsin.domain.model.StateUiModelMapper
import me.randheer.covidstatsin.domain.repo.StateRepository

class GetStateListUseCase(
    private val repository: StateRepository,
    private val mapper: StateUiModelMapper
) : CoroutineUseCase<GetStateListUseCase.Param, List<StateUiModel>> {

    override suspend fun run(input: Param): List<StateUiModel> {
        return repository.getStates(input.query).map { mapper.map(it) }
    }

    class Param(val query: String)
}