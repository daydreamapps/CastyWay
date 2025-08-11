package com.dda.castyway.ui.search

import com.dda.castyway.Downloader
import com.dda.castyway.shared.models.PodcastEpisode
import com.dda.castyway.shared.repositories.PodcastRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchPresenter(
    val podcastRepository: PodcastRepository,
    private val downloader: Downloader,
) {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    private val scope = CoroutineScope(Dispatchers.Main)

    fun search(query: String) {
        _state.update { it.copy(query = query, isLoading = true) }
        scope.launch {
            val podcasts = podcastRepository.searchForPodcastByName(query)
            _state.update {
                it.copy(
                    podcasts = podcasts,
                    isLoading = false,
                    errorMessage = if (podcasts.isEmpty() && query.isNotEmpty()) "No podcasts found" else null
                )
            }
        }
    }

    fun downloadEpisode(podcastEpisode: PodcastEpisode) {
        scope.launch {
            downloader.downloadFile(podcastEpisode.link, "${podcastEpisode.podcastName} - ${podcastEpisode.title}.mp3")
        }
    }
}