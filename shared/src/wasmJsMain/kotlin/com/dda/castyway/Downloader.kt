package com.dda.castyway

actual class Downloader actual constructor(private val context: PlatformContext) {
    actual fun downloadFile(url: String, fileName: String) {
        // TODO: Implement Wasm download logic
        println("Downloading on Wasm is not yet implemented.")
    }
}
