package com.dda.castyway.shared.network

import com.dda.castyway.shared.models.ListenNotesResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

open class ListenNotesApi(
    private val httpClient: HttpClient,
    private val apiKey: String
) {

    private val baseUrl = "https://listen-api.listennotes.com/api/v2"

    open suspend fun searchPodcasts(query: String): ListenNotesResponse {
        return httpClient.get("$baseUrl/search") {
            header("X-ListenAPI-Key", apiKey)
            parameter("q", query)
            parameter("type", "podcast")
        }.body()
    }
}
