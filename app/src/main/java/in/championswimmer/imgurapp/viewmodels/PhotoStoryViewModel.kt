package `in`.championswimmer.imgurapp.viewmodels

import `in`.championswimmer.libimgur.Imgur
import `in`.championswimmer.libimgur.models.GalleryPhotoItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotoStoryViewModel: ViewModel() {
    val photos = MutableLiveData<List<GalleryPhotoItem>>()

    fun refreshPhotoStory () {
        viewModelScope.launch {
            val gallery = Imgur.api.getGalleryByTag("science_and_tech")
            photos.postValue(gallery.data.items)
        }
    }
}