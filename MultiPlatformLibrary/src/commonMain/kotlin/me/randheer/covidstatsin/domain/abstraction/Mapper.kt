package me.randheer.covidstatsin.domain.abstraction

interface Mapper<in T, out R> {
    fun map(input: T): R
}