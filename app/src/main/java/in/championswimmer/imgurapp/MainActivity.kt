package `in`.championswimmer.imgurapp

import `in`.championswimmer.imgurapp.viewmodels.PhotoStoryViewModel
import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var photoStoryViewModel: PhotoStoryViewModel
    var currentAnimator: ObjectAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        photoStoryViewModel = ViewModelProviders.of(this).get(PhotoStoryViewModel::class.java)
        photoStoryViewModel.refreshPhotoStory()
        photoStoryViewModel.photoStream.observe(this, Observer {
            goToNextPhoto()
        })
    }

    /**
     * A function-calling-function to prevent recursion inside [goToNextPhoto]
     */
    private val callGoToNext = { it: Animator ->  goToNextPhoto() }

    fun goToNextPhoto() {
        photoStoryViewModel.photoStream.value?.pop()?.let {
            Glide.with(ivPhotoStory).load(it.link).into(ivPhotoStory)
            tvPhotoTitle.text = it.title
            currentAnimator =  ObjectAnimator.ofInt(progressPhotoStory, "progress", 100, 0).apply {
                duration = 4000
                interpolator = LinearInterpolator()
                start()
                doOnEnd (callGoToNext)
            }

            // Preload the next photo
            photoStoryViewModel.photoStream.value?.peek()?.let {
                Glide.with(ivPhotoStory).load(it.link).preload()
            }
        }

    }

    override fun onResume() {
        super.onResume()
        currentAnimator?.takeIf { it.isStarted && it.isPaused }?.resume()
    }

    override fun onPause() {
        currentAnimator?.takeIf { it.isStarted && it.isRunning }?.pause()
        super.onPause()
    }

}
