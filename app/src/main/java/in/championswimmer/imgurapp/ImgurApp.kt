package `in`.championswimmer.imgurapp

import `in`.championswimmer.imgurapp.di.components.DaggerAppComponent
import `in`.championswimmer.libimgur.di.modules.ImgurApiModule
import android.app.Application

class ImgurApp : Application() {

    public val appComponent = DaggerAppComponent.builder()
        .imgurApiModule(ImgurApiModule())
        .build()

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}