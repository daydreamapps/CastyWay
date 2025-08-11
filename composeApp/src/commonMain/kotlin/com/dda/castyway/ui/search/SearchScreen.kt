package com.dda.castyway

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dda.castyway.shared.repositories.Podcast
import com.dda.castyway.ui.search.SearchPresenter

@Composable
fun SearchScreen(
    presenter: SearchPresenter,
    onPodcastSelected: (Podcast) -> Unit,
) {
    val state by presenter.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = state.query,
            onValueChange = { presenter.search(it) },
            label = { Text("Search Podcasts") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> {
                Text(text = state.errorMessage!!)
            }

            state.podcasts.isEmpty() && state.query.isEmpty() -> {
                Text(text = "Enter a search query to find podcasts.")
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.podcasts) { podcast ->
                        PodcastListItem(podcast, onPodcastSelected)
                    }
                }
            }
        }
    }
}

@Composable
fun PodcastListItem(podcast: Podcast, onPodcastSelected: (Podcast) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .clickable { onPodcastSelected(podcast) }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = podcast.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}