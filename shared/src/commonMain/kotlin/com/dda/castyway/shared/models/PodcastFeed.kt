package com.dda.castyway.shared.models

import kotlinx.serialization.Serializable

@Serializable
data class PodcastFeed(
    val title: String,
    val link: String,
    val description: String,
    val episodes: List<PodcastEpisode>
)

@Serializable
data class PodcastEpisode(
    val title: String,
    val pubDate: String,
    val author: String,
    val link: String,
    val description: String,
    val duration: String
)
