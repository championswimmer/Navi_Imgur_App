package `in`.championswimmer.imgurapp.di.components

import `in`.championswimmer.libimgur.di.modules.ImgurApiModule
import `in`.championswimmer.libimgur.interfaces.ImgurAPI
import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [ImgurApiModule::class])
interface AppComponent {

    fun inject(application: Application)

    fun getImgurApi(): ImgurAPI

}