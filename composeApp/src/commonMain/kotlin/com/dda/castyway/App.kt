package com.dda.castyway

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dda.castyway.shared.repositories.PodcastRepository
import com.dda.castyway.shared.repositories.PodcastRepositoryImpl
import com.dda.castyway.ui.PodcastScreen
import com.dda.castyway.ui.PodcastViewModel
import com.dda.castyway.ui.search.SearchPresenter
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App(platformContext: PlatformContext) {
    MaterialTheme {
        val podcastViewModel: PodcastViewModel = viewModel { PodcastViewModel() }
        PodcastScreen(podcastViewModel)

        val podcastRepository = remember { PodcastRepositoryImpl() }
        val downloader = remember { Downloader(platformContext) }
        val presenter = remember { SearchPresenter(podcastRepository = podcastRepository, downloader) }
        SearchScreen(presenter)
    }
}