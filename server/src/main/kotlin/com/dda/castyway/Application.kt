package com.dda.castyway

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        get("/podcasts/search") {
            val query = call.request.queryParameters["query"] ?: ""
            val podcasts = PodcastRepository.search(query)
            call.respond(podcasts)
        }
        get("/podcasts/{id}/episodes") {
            val podcastId = call.parameters["id"]?.toIntOrNull()
            if (podcastId == null) {
                call.respond(emptyList<Episode>())
                return@get
            }
            val episodes = listOf(
                Episode(1, "Episode 1", "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
            )
            call.respond(episodes)
        }
    }
}