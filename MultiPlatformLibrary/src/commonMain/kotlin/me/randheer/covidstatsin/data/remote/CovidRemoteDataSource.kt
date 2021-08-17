package me.randheer.covidstatsin.data.remote

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import me.randheer.covidstatsin.data.model.CovidApiData

class CovidRemoteDataSource {

    companion object {
        private const val GET_COVID_PATH = "https://data.covid19india.org/v4/min/data.min.json"
    }

    private val client = HttpClient() {
        install(JsonFeature) {
            val json: Json = kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(json)
        }
    }


    suspend fun getCovidDate(): CovidApiData {
        return client.get(GET_COVID_PATH)
    }

}