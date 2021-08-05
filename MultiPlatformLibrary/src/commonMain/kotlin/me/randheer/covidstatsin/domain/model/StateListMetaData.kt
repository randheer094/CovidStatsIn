package me.randheer.covidstatsin.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class StateListMetaData(
    val title: String,
    val searchPlaceholder: String
)