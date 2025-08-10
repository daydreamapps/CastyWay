package com.dda.castyway

import kotlinx.serialization.Serializable

@Serializable
data class Podcast(
    val id: Int,
    val title: String,
    val author: String
)
