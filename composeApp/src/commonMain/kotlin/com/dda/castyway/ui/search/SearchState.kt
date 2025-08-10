package com.dda.castyway.ui.search

import com.dda.castyway.shared.repositories.Podcast

data class SearchState(
    val query: String = "",
    val podcasts: List<Podcast> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)