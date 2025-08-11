package com.dda.castyway.shared.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListenNotesResponse(
    val results: List<ListenNotesPodcast>
)

@Serializable
data class ListenNotesPodcast(
    val id: String,
    @SerialName("title_original")
    val title: String,
    @SerialName("publisher_original")
    val publisher: String,
    val image: String,
    val thumbnail: String,
    val rss: String
)
