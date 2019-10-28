package `in`.championswimmer.imgurapp

import `in`.championswimmer.imgurapp.enums.FetchStatus.FAILED
import `in`.championswimmer.imgurapp.enums.FetchStatus.FETCHING
import `in`.championswimmer.imgurapp.enums.FetchStatus.SUCCESS
import `in`.championswimmer.imgurapp.listadapters.CommentListAdapter
import `in`.championswimmer.imgurapp.listadapters.PhotoListAdapter
import `in`.championswimmer.imgurapp.viewmodels.AlbumDetailsViewModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import kotlinx.android.synthetic.main.activity_album_details.*

class AlbumDetailsActivity : AppCompatActivity() {

    companion object {
        const val BUNDLE_KEY_ALBUM_HASH = "album_hash"

        @JvmStatic
        fun start(activity: Activity, albumHash: String) {
            activity.startActivity(
                Intent(activity, AlbumDetailsActivity::class.java).apply {
                    putExtra(BUNDLE_KEY_ALBUM_HASH, albumHash)
                })
        }
    }

    lateinit var albumHash: String
    lateinit var albumDetailsViewModel: AlbumDetailsViewModel

    val photoListAdapter = PhotoListAdapter(arrayListOf())
    val commentListAdapter = CommentListAdapter(arrayListOf())

    private fun initViews() {
        vpPhotos.adapter = photoListAdapter
        TabLayoutMediator(tlIndicator, vpPhotos,
            TabConfigurationStrategy { tab, position ->
                tab.text = (position + 1).toString()
            }).attach()

        rvComments.layoutManager = LinearLayoutManager(this)
        rvComments.adapter = commentListAdapter
    }

    private fun initViewModel() {
        albumDetailsViewModel = ViewModelProviders.of(this).get(AlbumDetailsViewModel::class.java)
        albumDetailsViewModel.fetchStatus.observe(this, Observer {
            when (it) {
                FETCHING -> contentLoader.show()
                SUCCESS -> contentLoader.hide()
                FAILED -> {
                    contentLoader.hide()
                    Toast.makeText(this, "Could not fetch this album", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        })
        albumDetailsViewModel.loadAlbum(albumHash)
        albumDetailsViewModel.images.observe(this, Observer {
            photoListAdapter.updateData(it.toMutableList())

        })
        albumDetailsViewModel.comments.observe(this, Observer {
            commentListAdapter.updateData(it.toMutableList())
        })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)

        albumHash = intent.getStringExtra(BUNDLE_KEY_ALBUM_HASH) ?: ""
        if (albumHash.isEmpty()) {
            Log.e("Album", "Started with empty Album Hash")
            finish()
        }

        initViews()
        initViewModel()
    }
}
