package com.dda.castyway.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dda.castyway.shared.models.PodcastEpisode

@Composable
fun PodcastScreen(viewModel: PodcastViewModel) {
    val uiState by viewModel.uiState.collectAsState()

    // Load the podcast feed when the screen is first composed
    // For a real app, you might want to move this to a more appropriate lifecycle event
    // and avoid reloading on every recomposition.
    // For this example, we'll load it once.
    // A sample RSS feed url is used here.
    viewModel.loadPodcastFeed("https://feed.podbean.com/trashfuturepodcast/feed.xml")

    Surface(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is PodcastUiState.Loading -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator()
                }
            }
            is PodcastUiState.Success -> {
                PodcastList(state.feed.episodes)
            }
            is PodcastUiState.Error -> {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    Text(text = "Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
fun PodcastList(episodes: List<PodcastEpisode>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(episodes) { episode ->
            EpisodeListItem(episode)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun EpisodeListItem(episode: PodcastEpisode) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = episode.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Published: ${episode.pubDate}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Duration: ${episode.duration}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
