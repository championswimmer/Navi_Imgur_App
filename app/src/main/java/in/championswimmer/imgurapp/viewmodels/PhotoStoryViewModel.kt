package `in`.championswimmer.imgurapp.viewmodels

import `in`.championswimmer.imgurapp.enums.FetchStatus
import `in`.championswimmer.imgurapp.enums.FetchStatus.FAILED
import `in`.championswimmer.imgurapp.enums.FetchStatus.FETCHING
import `in`.championswimmer.imgurapp.enums.FetchStatus.NONE
import `in`.championswimmer.imgurapp.enums.FetchStatus.SUCCESS
import `in`.championswimmer.libimgur.Imgur
import `in`.championswimmer.libimgur.models.Image
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class PhotoStoryViewModel : ViewModel() {
    val photoStream = MutableLiveData<Stack<Image>>()
    val fetchStatus = MutableLiveData<FetchStatus>(NONE)

    fun refreshPhotoStory() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchStatus.postValue(FETCHING)
                val gallery = Imgur.api.getGalleryByTag("science_and_tech")
                val images = gallery.data?.items
                    ?.flatMap { p ->
                        p.images?.map {
                            it.apply {
                                title = title ?: p.title
                                parentItemId = p.id
                            }
                        } ?: listOf()
                    }
                    ?.filter { i -> i.type?.startsWith("image/") ?: false }
                fetchStatus.postValue(SUCCESS)
                photoStream.postValue(
                    Stack<Image>().apply { addAll(images ?: listOf()) }
                )
            } catch (e: Exception) {
                Log.e("PhotoStory", "Error fetching", e)
                fetchStatus.postValue(FAILED)
            }

        }

    }
}