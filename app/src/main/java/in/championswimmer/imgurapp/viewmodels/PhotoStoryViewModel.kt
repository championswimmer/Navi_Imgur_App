package `in`.championswimmer.imgurapp.viewmodels

import `in`.championswimmer.libimgur.Imgur
import `in`.championswimmer.libimgur.models.Image
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotoStoryViewModel : ViewModel() {
    val photoStream = MutableLiveData<List<Image>>()

    fun refreshPhotoStory() {
        viewModelScope.launch {
            val gallery = Imgur.api.getGalleryByTag("science_and_tech")
            photoStream.postValue(
                gallery.data.items
                    .flatMap { p -> p.images ?: listOf() }
                    .filter { i -> i.type.startsWith("image/") }
            )
        }
    }
}