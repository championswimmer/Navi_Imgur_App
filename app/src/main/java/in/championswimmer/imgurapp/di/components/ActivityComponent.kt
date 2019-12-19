package `in`.championswimmer.imgurapp.di.components

import `in`.championswimmer.imgurapp.di.scopes.ActivityScope
import `in`.championswimmer.imgurapp.ui.AlbumDetailsActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class])
interface ActivityComponent {

    fun inject(albumDetailsActivity: AlbumDetailsActivity)
}