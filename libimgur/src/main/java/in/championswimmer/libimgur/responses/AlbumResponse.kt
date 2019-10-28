package `in`.championswimmer.libimgur.responses


import `in`.championswimmer.libimgur.models.Album
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumResponse(
    @Json(name = "data")
    val `data`: Album?,
    @Json(name = "status")
    val status: Int,
    @Json(name = "success")
    val success: Boolean
)