package me.randheer.covidstatsin.data.local

import com.liftric.kvault.KVault
import me.randheer.covidstatsin.CovidStatsSharedApplication

actual fun getPrefs(): KVault {
    return KVault(CovidStatsSharedApplication.appCtx)
}