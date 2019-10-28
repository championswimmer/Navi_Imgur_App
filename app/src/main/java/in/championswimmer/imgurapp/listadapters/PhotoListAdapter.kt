package `in`.championswimmer.imgurapp.listadapters

import `in`.championswimmer.imgurapp.R
import `in`.championswimmer.imgurapp.listadapters.PhotoListAdapter.PhotoViewHolder
import `in`.championswimmer.libimgur.models.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_photo.view.*

class PhotoListAdapter(
    private val images: MutableList<Image>
) : ListAdapter<Image, PhotoViewHolder>(PhotoDiffCallback()) {

    fun updateData(newImages: List<Image>) {
        images.clear()
        images.addAll(newImages)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = images.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_photo, parent, false)

        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.itemView.ivPhoto.let {
            Glide.with(it)
                .load(images[position].link)
                .into(it)
        }
    }


    class PhotoViewHolder(itemView: View) : ViewHolder(itemView)

    class PhotoDiffCallback : ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean =
            oldItem == newItem

    }
}