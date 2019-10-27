package `in`.championswimmer.libimgur.interceptors

import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response

class AuthInterceptor: Interceptor {
    companion object {
        // TODO: Ideally better if it comes from BuildConfig.java
        private const val CLIENT_ID = "9854c6147c79e9d"
    }

    override fun intercept(chain: Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Authorization", "Client-ID $CLIENT_ID")
            .build()


        val response = chain.proceed(request)
        return response
    }
}