package `in`.championswimmer.imgurapp

import `in`.championswimmer.imgurapp.di.components.DaggerAppComponent
import android.app.Application

class ImgurApp: Application() {

    val appComponent = DaggerAppComponent.builder().build()

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }
}