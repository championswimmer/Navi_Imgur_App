package `in`.championswimmer.imgurapp.viewmodels

import `in`.championswimmer.libimgur.Imgur
import `in`.championswimmer.libimgur.models.Comment
import `in`.championswimmer.libimgur.models.Image
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDetailsViewModel : ViewModel() {
    val images = MutableLiveData<List<Image>>()
    val comments = MutableLiveData<List<Comment>>()

    fun loadAlbum(albumHash: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val album = Imgur.api.getAlbum(albumHash)
            val albumComments = Imgur.api.getAlbumComments(albumHash)

            album.data?.images?.let { images.postValue(it) }
            albumComments.data?.let { comments.postValue(it) }

        }
    }
}