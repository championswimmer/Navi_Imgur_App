package `in`.championswimmer.imgurapp.listadapters

import `in`.championswimmer.imgurapp.listadapters.CommentListAdapter.CommentViewHolder
import `in`.championswimmer.libimgur.models.Comment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CommentListAdapter(
    private val comments: List<Comment>
) : ListAdapter<Comment, CommentViewHolder>(CommentDiffCallback()) {

    override fun getItemCount(): Int  = comments.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)

        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(android.R.id.text1).text = comments[position].comment
    }

    class CommentViewHolder(itemView: View) : ViewHolder(itemView) {}


    class CommentDiffCallback : ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem == newItem

    }
}