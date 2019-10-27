package `in`.championswimmer.libimgur.models

import com.squareup.moshi.Json

data class Processing(
    @Json(name = "status")
    val status: String
)