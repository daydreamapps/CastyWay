package com.dda.castyway.shared.repositories

import com.dda.castyway.shared.models.PodcastEpisode
import com.dda.castyway.shared.models.PodcastFeed
import com.prof18.rssparser.RssParser
import com.prof18.rssparser.model.RssChannel
import com.prof18.rssparser.model.RssItem

interface PodcastRepository {
    suspend fun getPodcastFeed(url: String): PodcastFeed
}

class PodcastRepositoryImpl(private val rssParser: RssParser = RssParser()) : PodcastRepository {

    override suspend fun getPodcastFeed(url: String): PodcastFeed {
        val rssChannel: RssChannel = rssParser.getRssChannel(url)
        return rssChannel.toPodcastFeed()
    }
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
