package me.randheer.covidstatsin.android.details

object DistrictListScreenProps {
    private const val Path = "district-list/%s"
    const val Route = "district-list/{stateCode}"
    const val StateCode = "stateCode"

    fun getPath(stateCode: String): String {
        return Path.format(stateCode)
    }
}
