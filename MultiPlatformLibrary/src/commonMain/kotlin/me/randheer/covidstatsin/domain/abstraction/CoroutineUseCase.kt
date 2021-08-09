package me.randheer.covidstatsin.domain.abstraction

interface CoroutineUseCase<in T, out R> {
    @Throws(Exception::class)
    suspend fun run(input: T): R
}