package me.randheer.covidstatsin.data.local

import com.liftric.kvault.KVault

actual fun getPrefs(): KVault {
    return KVault("covid-stats", null)
}