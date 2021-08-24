package me.randheer.covidstatsin.domain.usecases

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.randheer.covidstatsin.domain.abstraction.CoroutineUseCase
import me.randheer.covidstatsin.domain.model.StateUiModel
import me.randheer.covidstatsin.domain.model.StateUiModelMapper
import me.randheer.covidstatsin.domain.repo.StateRepository
import me.randheer.covidstatsin.utils.isFrozen

class GetStateListUseCase(
    private val repository: StateRepository,
    private val mapper: StateUiModelMapper
) : CoroutineUseCase<GetStateListUseCase.Param, List<StateUiModel>> {

    @Throws(Exception::class)
    override suspend fun run(input: Param): List<StateUiModel> {
        val result = withContext(Dispatchers.Default) {
            println("input is frozen = ${isFrozen(input)}")
            repository.getStates(input.query).map { mapper.map(it) }
        }
        return withContext(Dispatchers.Main) {
            println("result is frozen = ${isFrozen(result)}")
            result
        }
    }

    class Param(val query: String)
}