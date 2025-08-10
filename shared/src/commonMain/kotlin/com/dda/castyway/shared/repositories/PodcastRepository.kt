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
        Podcast(1, "The Joe Rogan Experience", "Joe Rogan"),
        Podcast(2, "This American Life", "WBEZ Chicago"),
        Podcast(3, "The Daily", "The New York Times"),
        Podcast(4, "Stuff You Should Know", "iHeartPodcasts"),
        Podcast(5, "My Favorite Murder", "Exactly Right"),
        Podcast(6, "Crime Junkie", "audiochuck"),
        Podcast(7, "The Ben Shapiro Show", "The Daily Wire"),
        Podcast(8, "Serial", "Serial Productions"),
        Podcast(9, "Radiolab", "WNYC Studios"),
        Podcast(10, "Hidden Brain", "NPR")
    )
}

private fun RssChannel.toPodcastFeed(): PodcastFeed {
    return PodcastFeed(
        title = title.orEmpty(),
        link = link.orEmpty(),
        description = description.orEmpty(),
        episodes = items.map(RssItem::toPodcastEpisode),
    )
}

private fun RssItem.toPodcastEpisode(): PodcastEpisode {
    return PodcastEpisode(
        title = title.orEmpty(),
        pubDate = pubDate.orEmpty(),
        author = author.orEmpty(),
        link = link.orEmpty(),
        description = description.orEmpty(),
        duration = "", // TODO
    )
}
