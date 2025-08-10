package com.dda.castyway

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchPresenter(
    private val podcastService: PodcastService
) {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Main)

    fun search(query: String) {
        _state.update { it.copy(query = query, isLoading = true) }
        scope.launch {
            val podcasts = podcastService.searchPodcasts(query)
            _state.update {
                it.copy(
                    podcasts = podcasts,
                    isLoading = false,
                    errorMessage = if (podcasts.isEmpty() && query.isNotEmpty()) "No podcasts found" else null
                )
            }
        }
    }
}
