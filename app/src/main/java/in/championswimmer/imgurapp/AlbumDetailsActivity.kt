package `in`.championswimmer.imgurapp

import `in`.championswimmer.imgurapp.listadapters.CommentListAdapter
import `in`.championswimmer.imgurapp.viewmodels.AlbumDetailsViewModel
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_details)


        albumDetailsViewModel = ViewModelProviders.of(this).get(AlbumDetailsViewModel::class.java)

        albumHash = intent.getStringExtra(BUNDLE_KEY_ALBUM_HASH) ?: ""
        if (albumHash.isEmpty()) {
            Log.e("Album", "Started with empty Album Hash")
            finish()
        }

        albumDetailsViewModel.loadAlbum(albumHash)

        albumDetailsViewModel.comments.observe(this, Observer {
            rvComments.layoutManager = LinearLayoutManager(this)
            rvComments.adapter = CommentListAdapter(it)
        })


    }
}
