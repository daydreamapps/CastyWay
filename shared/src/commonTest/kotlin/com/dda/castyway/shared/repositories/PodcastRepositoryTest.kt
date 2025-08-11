package com.dda.castyway.shared.repositories

import com.dda.castyway.shared.models.ListenNotesPodcast
import com.dda.castyway.shared.models.ListenNotesResponse
import com.dda.castyway.shared.network.ListenNotesApi
import io.ktor.client.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PodcastRepositoryTest {

    @Test
    fun `searchForPodcastByName should return a list of podcasts`() = runTest {
        // Given
        val fakeListenNotesApi = FakeListenNotesApi()
        val podcastRepository = PodcastRepositoryImpl(listenNotesApi = fakeListenNotesApi)
        val query = "test"

        // When
        val podcasts = podcastRepository.searchForPodcastByName(query)

        // Then
        assertEquals(1, podcasts.size)
        assertEquals("Test Podcast", podcasts.first().title)
        assertEquals("test_rss", podcasts.first().channelUrl)
    }
}

class FakeListenNotesApi : ListenNotesApi(HttpClient(), "") {

    override suspend fun searchPodcasts(query: String): ListenNotesResponse {
        return ListenNotesResponse(
            results = listOf(
                ListenNotesPodcast(
                    id = "test_id",
                    title = "Test Podcast",
                    publisher = "Test Publisher",
                    image = "test_image",
                    thumbnail = "test_thumbnail",
                    rss = "test_rss"
                )
            )
        )
    }
}
