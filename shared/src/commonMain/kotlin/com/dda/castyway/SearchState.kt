package com.dda.castyway

data class SearchState(
    val query: String = "",
    val podcasts: List<Podcast> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
