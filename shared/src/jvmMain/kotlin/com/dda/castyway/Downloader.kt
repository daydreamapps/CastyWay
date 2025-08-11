package com.dda.castyway

import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption

actual class Downloader actual constructor(private val context: PlatformContext) {
    actual fun downloadFile(url: String, fileName: String) {
        val homeDir = System.getProperty("user.home")
        val downloadsDir = File(homeDir, "Downloads")
        if (!downloadsDir.exists()) {
            downloadsDir.mkdir()
        }
        val destination = File(downloadsDir, fileName)
        URL(url).openStream().use {
            Files.copy(it, destination.toPath(), StandardCopyOption.REPLACE_EXISTING)
        }
        println("File downloaded to: ${destination.absolutePath}")
    }
}
