package com.dda.castyway.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dda.castyway.Downloader
import com.dda.castyway.shared.models.PodcastEpisode
import com.dda.castyway.shared.models.PodcastFeed
import com.dda.castyway.shared.repositories.PodcastRepository
import com.dda.castyway.shared.repositories.PodcastRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.dda.castyway.shared.network.ListenNotesApi


sealed interface PodcastUiState {
    data object Loading : PodcastUiState
    data class Success(val feed: PodcastFeed) : PodcastUiState
    data class Error(val message: String) : PodcastUiState
}

class PodcastViewModel(
    private val podcastRepository: PodcastRepository = PodcastRepositoryImpl(
        listenNotesApi = ListenNotesApi()
    ),
    private val downloader: Downloader,
) : ViewModel() {

    private val _uiState = MutableStateFlow<PodcastUiState>(PodcastUiState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadPodcastFeed(url: String) {
        viewModelScope.launch {
            _uiState.value = PodcastUiState.Loading
            try {
                val feed = podcastRepository.getPodcastFeed(url)
                _uiState.value = PodcastUiState.Success(feed)
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.value = PodcastUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun downloadEpisode(podcastEpisode: PodcastEpisode) {
        viewModelScope.launch {
            downloader.downloadFile(podcastEpisode.contentLink!!, "${podcastEpisode.podcastName} - ${podcastEpisode.title}.mp3")
        }
    }
}
