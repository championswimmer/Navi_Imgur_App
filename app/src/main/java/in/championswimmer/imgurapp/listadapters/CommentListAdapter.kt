package `in`.championswimmer.imgurapp.listadapters

import `in`.championswimmer.imgurapp.R
import `in`.championswimmer.imgurapp.listadapters.CommentListAdapter.CommentViewHolder
import `in`.championswimmer.libimgur.models.Comment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.list_item_comment.view.*

class CommentListAdapter(
    private val comments: MutableList<Comment>
) : ListAdapter<Comment, CommentViewHolder>(CommentDiffCallback()) {

    fun updateData(newComments: List<Comment>) {
        comments.clear()
        comments.addAll(newComments)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = comments.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_comment, parent, false)

        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.itemView.tvUsername.text = comments[position].author
        holder.itemView.tvComment.text = comments[position].comment
        holder.itemView.tvPts.text = String.format(
            holder.itemView.resources.getString(R.string.comment_pts),
            comments[position].points ?: 0
        )
    }

    class CommentViewHolder(itemView: View) : ViewHolder(itemView) {}


    class CommentDiffCallback : ItemCallback<Comment>() {
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem === newItem

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean =
            oldItem == newItem

    }
}