package com.dda.castyway

import com.dda.castyway.shared.SERVER_PORT
import com.dda.castyway.shared.repositories.Podcast
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

class PodcastService {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun searchPodcasts(query: String): List<Podcast> {
        return try {
            client.get("http://localhost:$SERVER_PORT/podcasts/search") {
                parameter("query", query)
            }.body()
        } catch (e: Exception) {
            println("Error fetching podcasts: ${e.message}")
            emptyList()
        }
    }

    suspend fun getEpisodes(podcastId: Int): List<Episode> {
        return try {
            client.get("http://localhost:$SERVER_PORT/podcasts/$podcastId/episodes").body()
        } catch (e: Exception) {
            println("Error fetching episodes: ${e.message}")
            emptyList()
        }
    }
}
