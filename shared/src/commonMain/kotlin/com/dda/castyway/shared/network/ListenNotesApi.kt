package com.dda.castyway.shared.network

import com.dda.castyway.shared.models.ListenNotesResponse

expect class ListenNotesApi {
    suspend fun searchPodcasts(query: String): ListenNotesResponse
}

