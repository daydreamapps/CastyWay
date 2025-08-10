package com.dda.castyway.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dda.castyway.shared.models.PodcastFeed
import com.dda.castyway.shared.repositories.PodcastRepository
import com.dda.castyway.shared.repositories.PodcastRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface PodcastUiState {
    data object Loading : PodcastUiState
    data class Success(val feed: PodcastFeed) : PodcastUiState
    data class Error(val message: String) : PodcastUiState
}

class PodcastViewModel(
    private val podcastRepository: PodcastRepository = PodcastRepositoryImpl()
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
}
