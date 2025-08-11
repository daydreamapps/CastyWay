package com.dda.castyway.shared.network

import android.util.Log
import com.dda.castyway.shared.models.ListenNotesResponse
import com.listennotes.podcast_api.Client
import com.listennotes.podcast_api.exception.AuthenticationException
import com.listennotes.podcast_api.exception.ListenApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

actual class ListenNotesApi {

    actual suspend fun searchPodcasts(query: String): ListenNotesResponse {
        withContext(Dispatchers.IO) {
            try {
                // If apiKey is not set or an empty string, then we'll connect
                // to the api mock server, which returns fake data for testing
                val apiKey: String = System.getenv("LISTEN_API_KEY") ?: ""
                val client = Client(apiKey)

                // Parameters are passed via this HashMap
                val parameters = hashMapOf(
                    "q" to query,
                )

                Log.d("podcastsearch", "query: $query")
                // response.toJSON() returns an org.json.JSONObject
                val response = client.search(parameters)

                println(response.toJSON())

            } catch (e: AuthenticationException) {
                e.printStackTrace()
            } catch (e: ListenApiException) {
                e.printStackTrace()
            } finally {
                TODO()
            }
        }
    }
}