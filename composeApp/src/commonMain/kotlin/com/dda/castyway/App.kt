package com.dda.castyway

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dda.castyway.shared.network.ListenNotesApi
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
                val podcastRepository = remember {
                    val listenNotesApi = ListenNotesApi()
                    PodcastRepositoryImpl(listenNotesApi = listenNotesApi)
                }
                val presenter = remember { SearchPresenter(podcastRepository = podcastRepository) }
                SearchScreen(presenter, onPodcastSelected = { selectedPodcast = it })
            }

            else -> {
                val downloader = remember { Downloader(platformContext) }
                val podcastViewModel: PodcastViewModel = viewModel { PodcastViewModel(downloader = downloader) }
                PodcastScreen(podcastViewModel, podcast)
            }
        }
    }
}