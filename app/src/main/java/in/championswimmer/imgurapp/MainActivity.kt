package `in`.championswimmer.imgurapp

import `in`.championswimmer.imgurapp.viewmodels.PhotoStoryViewModel
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    lateinit var photoStoryViewModel: PhotoStoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photoStoryViewModel = ViewModelProviders.of(this).get(PhotoStoryViewModel::class.java)
        photoStoryViewModel.refreshPhotoStory()
        photoStoryViewModel.photos.observe(this, Observer {

            Toast.makeText(this, "${it.size}", Toast.LENGTH_SHORT).show()

        })

    }
}
