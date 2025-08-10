package com.dda.castyway

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dda.castyway.ui.PodcastScreen
import com.dda.castyway.ui.PodcastViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val podcastViewModel: PodcastViewModel = viewModel { PodcastViewModel() }
        PodcastScreen(podcastViewModel)
    }
}