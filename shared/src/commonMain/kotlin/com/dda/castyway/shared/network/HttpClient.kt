package com.dda.castyway.shared.network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*

fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            // We don't register XML because we are decoding it manually.
        }
    }
}
