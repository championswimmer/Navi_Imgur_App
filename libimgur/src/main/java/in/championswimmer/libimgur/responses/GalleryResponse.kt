package `in`.championswimmer.libimgur.responses


import `in`.championswimmer.libimgur.models.GalleryData
import com.squareup.moshi.Json

data class GalleryResponse(
    @Json(name = "data")
    val `data`: GalleryData,
    @Json(name = "status")
    val status: Int,
    @Json(name = "success")
    val success: Boolean
) {
}