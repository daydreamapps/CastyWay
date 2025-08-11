package com.dda.castyway

expect class PlatformContext

expect class Downloader(context: PlatformContext) {
    fun downloadFile(url: String, fileName: String)
}
