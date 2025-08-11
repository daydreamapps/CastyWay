package com.dda.castyway

import com.dda.castyway.shared.repositories.Podcast

object PodcastRepository {

    private val podcasts = listOf(
        Podcast(1, "The Joe Rogan Experience", "Joe Rogan", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"),
        Podcast(2, "This American Life", "WBEZ Chicago", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"),
        Podcast(3, "The Daily", "The New York Times", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3"),
        Podcast(4, "Stuff You Should Know", "iHeartPodcasts", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-4.mp3"),
        Podcast(5, "My Favorite Murder", "Exactly Right", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-5.mp3"),
        Podcast(6, "Crime Junkie", "audiochuck", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-6.mp3"),
        Podcast(7, "The Ben Shapiro Show", "The Daily Wire", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-7.mp3"),
        Podcast(8, "Serial", "Serial Productions", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3"),
        Podcast(9, "Radiolab", "WNYC Studios", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-9.mp3"),
        Podcast(10, "Hidden Brain", "NPR", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-10.mp3")
    )

    fun search(query: String): List<Podcast> {
        return if (query.isBlank()) {
            emptyList()
        } else {
            podcasts.filter { it.title.contains(query, ignoreCase = true) }
        }
    }
}
