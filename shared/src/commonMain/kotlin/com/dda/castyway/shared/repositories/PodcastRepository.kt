package com.dda.castyway.shared.repositories

import com.dda.castyway.shared.models.Rss
import com.dda.castyway.shared.network.createHttpClient
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import nl.adaptivity.xmlutil.serialization.XML

interface PodcastRepository {
    suspend fun getPodcastFeed(url: String): Rss
}

class PodcastRepositoryImpl(private val httpClient: HttpClient = createHttpClient()) : PodcastRepository {
    override suspend fun getPodcastFeed(url: String): Rss {
        val response = httpClient.get(url)
        val responseBody = response.bodyAsText()
        val xml = XML {
            defaultPolicy {
                ignoreUnknownChildren()
            }
        }
        return xml.decodeFromString(responseBody)
    }
}
