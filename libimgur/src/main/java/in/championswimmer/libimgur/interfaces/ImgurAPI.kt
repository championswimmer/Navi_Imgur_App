package `in`.championswimmer.libimgur.interfaces

import `in`.championswimmer.libimgur.responses.AlbumCommentsResponse
import `in`.championswimmer.libimgur.responses.AlbumResponse
import `in`.championswimmer.libimgur.responses.GalleryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ImgurAPI {

    @GET("gallery/t/{tag}/{sort}/{page}")
    suspend fun getGalleryByTag(
        @Path("tag") tag: String,
        @Path("sort") sortBy: String = "time",
        @Path("page") pageNum: Int = 1
    ): GalleryResponse

    @GET("album/{albumHash}")
    suspend fun getAlbum(
        @Path("albumHash") albumHash: String
    ): AlbumResponse

    @GET("album/{albumHash}/comments")
    suspend fun getAlbumComments(
        @Path("albumHash") albumHash: String
    ): AlbumCommentsResponse
}