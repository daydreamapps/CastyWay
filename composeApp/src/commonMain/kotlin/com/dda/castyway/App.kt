package com.dda.castyway

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dda.castyway.shared.repositories.Podcast
import com.dda.castyway.shared.repositories.PodcastRepositoryImpl
import com.dda.castyway.ui.PodcastScreen
import com.dda.castyway.ui.PodcastViewModel
import com.dda.castyway.ui.search.SearchPresenter

@Composable
fun App(platformContext: PlatformContext) {
    MaterialTheme {

        var selectedPodcast by remember { mutableStateOf<Podcast?>(null) }

        when (val podcast = selectedPodcast) {
            null -> {
                val podcastRepository = remember { PodcastRepositoryImpl() }
                val downloader = remember { Downloader(platformContext) }
                val presenter = remember { SearchPresenter(podcastRepository = podcastRepository, downloader) }
                SearchScreen(presenter, onPodcastSelected = { selectedPodcast = it })
            }

            else -> {
                val podcastViewModel: PodcastViewModel = viewModel { PodcastViewModel() }
                PodcastScreen(podcastViewModel, podcast)
            }
        }
    }
}