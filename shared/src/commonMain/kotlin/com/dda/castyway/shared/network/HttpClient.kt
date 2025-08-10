package com.dda.castyway.shared.network

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import nl.adaptivity.xmlutil.serialization.XML

fun createHttpClient(): HttpClient {
    return HttpClient {
        install(ContentNegotiation) {
            // We don't register XML because we are decoding it manually.
        }
    }
}
