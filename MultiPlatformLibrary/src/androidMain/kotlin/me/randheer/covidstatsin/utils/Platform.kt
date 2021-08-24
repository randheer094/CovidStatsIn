package me.randheer.covidstatsin.utils

actual fun isFrozen(obj: Any): Boolean {
    println(
        """
            Current Thread:: 
            thread= ${Thread.currentThread().id}             
            name = ${Thread.currentThread().name}
        """.trimIndent()
    )
    return false
}