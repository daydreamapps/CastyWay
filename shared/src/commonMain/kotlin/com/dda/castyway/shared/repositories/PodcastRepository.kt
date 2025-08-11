package com.dda.castyway.shared.repositories

import com.dda.castyway.shared.models.PodcastEpisode
import com.dda.castyway.shared.models.PodcastFeed
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel
import com.prof18.rssparser.model.RssItem
import kotlin.collections.emptyList

interface PodcastRepository {
    suspend fun getPodcastFeed(url: String): PodcastFeed
    suspend fun searchForPodcastByName(name: String): List<Podcast>
}

class PodcastRepositoryImpl(private val rssParser: RssParser = RssParser()) : PodcastRepository {

    override suspend fun getPodcastFeed(url: String): PodcastFeed {
        val rssChannel: RssChannel = rssParser.getRssChannel(url)
        return rssChannel.toPodcastFeed()
    }

    override suspend fun searchForPodcastByName(name: String): List<Podcast> {
        return if (name.isBlank()) {
            emptyList()
        } else {
            podcasts.filter { it.title.contains(name, ignoreCase = true) }
        }
    }

    private val podcasts = listOf(
        Podcast(1, "Trashfuture", "https://feed.podbean.com/trashfuturepodcast/feed.xml"),
    )
}

private fun RssChannel.toPodcastFeed(): PodcastFeed {
    return PodcastFeed(
        title = title.orEmpty(),
        link = link.orEmpty(),
        description = description.orEmpty(),
        episodes = items.map({ it.toPodcastEpisode(podcastName = title.orEmpty()) }),
    )
}

private fun RssItem.toPodcastEpisode(podcastName: String): PodcastEpisode {
    println(this.toString())
    return PodcastEpisode(
        podcastName = podcastName, // required for file names
        title = title.orEmpty(),
        pubDate = pubDate.orEmpty(),
        author = author.orEmpty(),
        link = link.orEmpty(),
        contentLink = rawEnclosure?.url,
        contentSizeBytes = rawEnclosure?.length,
        contentType = rawEnclosure?.type,
        description = description.orEmpty(),
    )
}
