package me.randheer.covidstatsin.android.navigation

import me.randheer.covidstatsin.android.details.DistrictListScreenProps
import me.randheer.covidstatsin.android.states.StateListScreenProps

sealed class Screens(val route: String) {
    object StateList : Screens(StateListScreenProps.Route)
    object DistrictList : Screens(DistrictListScreenProps.Route)
}