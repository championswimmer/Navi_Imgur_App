package `in`.championswimmer.imgurapp

import `in`.championswimmer.imgurapp.viewmodels.PhotoStoryViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var photoStoryViewModel: PhotoStoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photoStoryViewModel = ViewModelProviders.of(this).get(PhotoStoryViewModel::class.java)
        photoStoryViewModel.refreshPhotoStory()
        photoStoryViewModel.photoStream.observe(this, Observer {

            Glide
                .with(ivPhotoStory)
                .load(it[0].link)
                .into(ivPhotoStory)

        })

    }
}
