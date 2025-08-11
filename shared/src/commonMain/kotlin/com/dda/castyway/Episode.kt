package com.dda.castyway

import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: Int,
    val title: String,
    val url: String
)
