package `in`.championswimmer.imgurapp.viewmodels

import `in`.championswimmer.libimgur.Imgur
import `in`.championswimmer.libimgur.models.Image
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class PhotoStoryViewModel : ViewModel() {
    val photoStream = MutableLiveData<Stack<Image>>()


    fun refreshPhotoStory() {

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val gallery = Imgur.api.getGalleryByTag("science_and_tech")
                val images = gallery.data.items
                    ?.flatMap { p ->
                        p.images?.map {
                            it.apply {
                                title = title ?: p.title
                            }
                        } ?: listOf()
                    }
                    ?.filter { i -> i.type?.startsWith("image/") ?: false }

                photoStream.postValue(
                    Stack<Image>().apply { addAll(images ?: listOf()) }
                )
            }

        }
    }
}