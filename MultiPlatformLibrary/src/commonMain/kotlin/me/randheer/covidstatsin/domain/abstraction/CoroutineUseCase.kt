package me.randheer.covidstatsin.domain.abstraction

interface CoroutineUseCase<in T, out R> {
    suspend fun run(input: T): R
}