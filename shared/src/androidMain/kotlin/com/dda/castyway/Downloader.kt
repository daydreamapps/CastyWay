package com.dda.castyway

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log

actual class PlatformContext(
    val context: Context,
)

actual class Downloader actual constructor(private val context: PlatformContext) {

    actual fun downloadFile(url: String, fileName: String) {
        Log.d("Downloader", "downloading $url")
        val downloadManager = context.context.getSystemService(DownloadManager::class.java)
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(fileName)
            .setDescription("Downloading")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        downloadManager.enqueue(request)
    }
}
