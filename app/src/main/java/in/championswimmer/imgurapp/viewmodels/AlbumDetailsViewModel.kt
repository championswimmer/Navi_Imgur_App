package `in`.championswimmer.imgurapp.viewmodels

import `in`.championswimmer.imgurapp.enums.FetchStatus
import `in`.championswimmer.imgurapp.enums.FetchStatus.FAILED
import `in`.championswimmer.imgurapp.enums.FetchStatus.FETCHING
import `in`.championswimmer.imgurapp.enums.FetchStatus.NONE
import `in`.championswimmer.imgurapp.enums.FetchStatus.SUCCESS
import `in`.championswimmer.libimgur.Imgur
import `in`.championswimmer.libimgur.models.Comment
import `in`.championswimmer.libimgur.models.Image
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class AlbumDetailsViewModel : ViewModel() {
    val images = MutableLiveData<List<Image>>()
    val comments = MutableLiveData<List<Comment>>()
    val fetchStatus = MutableLiveData<FetchStatus>(NONE)

    fun loadAlbum(albumHash: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                fetchStatus.postValue(FETCHING)
                val album = Imgur.api.getAlbum(albumHash)
                val albumComments = Imgur.api.getAlbumComments(albumHash)

                album.data?.images?.let { images.postValue(it) }
                albumComments.data?.let { comments.postValue(it) }
                fetchStatus.postValue(SUCCESS)
            } catch (e: Exception) {
                Log.e("Album", "Error fetching", e)
                fetchStatus.postValue(FAILED)
            }

        }
    }
}