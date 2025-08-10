package com.dda.castyway.shared.repositories

import kotlinx.serialization.Serializable

@Serializable
data class Podcast(
    val id: Int,
    val title: String,
    val author: String
)