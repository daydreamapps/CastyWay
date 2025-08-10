package com.dda.castyway

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform