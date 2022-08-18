package ch.b.retrofitandcoroutines.certain_post.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.databinding.CommentsItemBinding

class CommentsAdapter : RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    private val comments = ArrayList<String>()
    private val authorOfComments = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(new: java.util.ArrayList<String>,newTwo: java.util.ArrayList<String>){
        comments.clear()
        authorOfComments.clear()
        comments.addAll(new)
        authorOfComments.addAll(newTwo)
        notifyDataSetChanged()
    }

    inner class CommentsViewHolder(private val binding: CommentsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindComments(comments: String) {
            Log.i("CUM", "$comments in bind")
            binding.comment.text = comments
        }
        fun bindAuthorOfComments(author: String){
            binding.authorOfComments.text = author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CommentsItemBinding.inflate(layoutInflater, parent, false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.bindComments(comments[position])
        holder.bindAuthorOfComments(authorOfComments[position])
    }

    override fun getItemCount(): Int = comments.size
}