package me.randheer.covidstatsin.android.navigation

sealed class Screens(val route: String) {
    object StateList : Screens("state-list")
    object DistrictList : Screens("district-list")
}