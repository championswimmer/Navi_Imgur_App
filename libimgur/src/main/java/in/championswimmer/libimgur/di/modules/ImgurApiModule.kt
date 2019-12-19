package `in`.championswimmer.libimgur.di.modules

import `in`.championswimmer.libimgur.interceptors.AuthInterceptor
import `in`.championswimmer.libimgur.interfaces.ImgurAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ImgurApiModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(AuthInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.imgur.com/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideImgurApi(retrofit: Retrofit): ImgurAPI {
        return retrofit.create(ImgurAPI::class.java)
    }
}