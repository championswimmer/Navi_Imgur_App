package `in`.championswimmer.imgurapp.utils

import `in`.championswimmer.libimgur.models.Image
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

object ImageDownloader {

    @JvmStatic
    fun downloadImage(context: Context, image: Image) {
        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val downloadRequest = DownloadManager.Request(Uri.parse(image.link)).apply {
            val imageExt = image.type?.split("/")?.get(1)

            setMimeType(image.type)
            setTitle(image.title)
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            setDescription(image.description ?: "Image from Imgur")
            setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES, "${image.id}.${imageExt}")

        }
        downloadManager.enqueue(downloadRequest)
    }
}