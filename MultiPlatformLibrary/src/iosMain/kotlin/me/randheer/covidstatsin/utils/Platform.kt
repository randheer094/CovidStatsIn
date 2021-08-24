package me.randheer.covidstatsin.utils

import platform.Foundation.NSThread
import kotlin.native.concurrent.isFrozen
import kotlin.native.identityHashCode

actual fun isFrozen(obj: Any): Boolean {
    println(
        """
            Current Thread:: 
            thread= ${NSThread.currentThread.identityHashCode()}             
            isMainThread = ${NSThread.isMainThread}
        """.trimIndent()
    )
    return obj.isFrozen
}