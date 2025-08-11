package com.dda.castyway

actual class Downloader actual constructor(private val context: PlatformContext) {
    actual fun downloadFile(url: String, fileName: String) {
        // TODO: Implement iOS download logic
        println("Downloading on iOS is not yet implemented.")
    }
}
