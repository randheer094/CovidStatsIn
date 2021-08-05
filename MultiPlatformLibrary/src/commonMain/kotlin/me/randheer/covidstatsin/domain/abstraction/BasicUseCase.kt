package me.randheer.covidstatsin.domain.abstraction

interface BasicUseCase<T, R> {
    fun run(input: T): R
}