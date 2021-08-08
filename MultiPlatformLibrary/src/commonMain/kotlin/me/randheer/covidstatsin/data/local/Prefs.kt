package me.randheer.covidstatsin.data.local

import com.liftric.kvault.KVault
import com.squareup.sqldelight.internal.Atomic

expect fun getPrefs(): KVault

object Prefs {
    private var ref: Atomic<KVault?> = Atomic(null)

    private fun setUp() {
        ref.set(getPrefs())
    }

    fun getRef(): KVault {
        if (ref.get() == null) setUp()
        return ref.get()!!
    }
}