package `in`.championswimmer.libimgur.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Comment(
    @Json(name = "album_cover")
    val albumCover: String?,
    @Json(name = "author")
    val author: String?,
    @Json(name = "author_id")
    val authorId: Int?,
    @Json(name = "children")
    val children: List<Comment>?,
    @Json(name = "comment")
    val comment: String?,
    @Json(name = "datetime")
    val datetime: Int?,
    @Json(name = "deleted")
    val deleted: Boolean?,
    @Json(name = "downs")
    val downs: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "image_id")
    val imageId: String?,
    @Json(name = "on_album")
    val onAlbum: Boolean?,
    @Json(name = "parent_id")
    val parentId: Int?,
    @Json(name = "platform")
    val platform: String?,
    @Json(name = "points")
    val points: Int?,
    @Json(name = "ups")
    val ups: Int?,
    @Json(name = "vote")
    val vote: Any?
)