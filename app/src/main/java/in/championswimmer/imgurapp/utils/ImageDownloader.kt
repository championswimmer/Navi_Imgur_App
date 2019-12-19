package `in`.championswimmer.imgurapp.utils

import `in`.championswimmer.imgurapp.ui.MainActivity
import `in`.championswimmer.libimgur.models.Image
import android.Manifest
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object ImageDownloader {


    /**
     * Initiates image download via the [DownloadManager]
     * Although we have the current bitmap fetched by Glide, when user wants to download
     * let us go to the original URI and download the original sized image as it is.
     *
     * TODO: If permission is not available, we have asked, but not continued the download
     *       after the permission is granted. For better UX, the original image because of which
     *       permission was asked should start downloading after permission grant.
     */
    @JvmStatic
    fun initiateImageDownload(activity: Activity, image: Image) {
        when (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            PackageManager.PERMISSION_GRANTED -> downloadImage(activity, image)
            PackageManager.PERMISSION_DENIED -> {
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    MainActivity.PERM_REQ_CODE
                )
            }
        }
    }

    @JvmStatic
    private fun downloadImage(context: Context, image: Image) {
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