package ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.databinding.PhotographerItemBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.DiffUtilCallback
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI


class FavouriteAdapter : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    private val favouritePost = ArrayList<PhotographerUI>()

    fun update(newList: List<PhotographerUI>) {
        val diffCallback = DiffUtilCallback(favouritePost, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        favouritePost.clear()
        favouritePost.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    inner class FavouriteViewHolder(private val binding: PhotographerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photographer: PhotographerUI) {
            photographer.mapSuccess(
                binding.authorName,
                binding.like,
                binding.imageView,
                binding.itemPostShowAllComments,
                binding.someComment
            )
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteAdapter.FavouriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  PhotographerItemBinding.inflate(layoutInflater, parent, false)
        return FavouriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.FavouriteViewHolder, position: Int) {
        holder.bind(favouritePost[position])
    }

    override fun getItemCount(): Int = favouritePost.size

}