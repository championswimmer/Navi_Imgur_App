package `in`.championswimmer.libimgur.interfaces

import `in`.championswimmer.libimgur.responses.GalleryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ImgurAPI {

    @GET("gallery/t/{tag}")
    suspend fun getGalleryByTag(
        @Path("tag") tag: String
    ): GalleryResponse
}