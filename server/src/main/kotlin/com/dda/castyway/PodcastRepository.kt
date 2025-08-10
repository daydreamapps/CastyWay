package com.dda.castyway

object PodcastRepository {

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

    fun search(query: String): List<Podcast> {
        return if (query.isBlank()) {
            emptyList()
        } else {
            podcasts.filter { it.title.contains(query, ignoreCase = true) }
        }
    }
}
