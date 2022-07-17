package ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.EmptydataFullscreenBinding
import ch.b.retrofitandcoroutines.databinding.PhotographerItemBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.DiffUtilCallback
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import java.lang.IllegalStateException


class FavouriteAdapter(private val favouriteClick: FavouriteItemClickListener) :
    RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    private val favouritePost = ArrayList<PhotographerUI>()

    fun update(newList: List<PhotographerUI>) {
        val diffCallback = DiffUtilCallback(favouritePost, newList)
        val result = DiffUtil.calculateDiff(diffCallback)
        favouritePost.clear()
        favouritePost.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int = when (favouritePost[position]) {
        is PhotographerUI.Base -> 0
        is PhotographerUI.EmptyData -> 1
        else -> 2
    }

    abstract class FavouriteViewHolder(binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        open fun bind(photographer: PhotographerUI) = Unit

        class Base(
            private val binding: PhotographerItemBinding,
            private val favouriteClick: FavouriteItemClickListener
        ) : FavouriteViewHolder(binding) {
            override fun bind(photographer: PhotographerUI) {
                binding.itemPostCollect.setImageResource(R.drawable.bookmark)
                photographer.mapSuccess(
                    binding.authorName,
                    binding.like,
                    binding.imageView,
                    binding.itemPostShowAllComments,
                    binding.someComment
                )
                binding.itemPostCollect.setOnClickListener {
                    favouriteClick.deleteClick(photographer.mapId())
                }
            }
        }

        class Empty(binding: EmptydataFullscreenBinding) : FavouriteViewHolder(binding)

    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingBase = PhotographerItemBinding.inflate(layoutInflater, parent, false)
        val bindingEmpty = EmptydataFullscreenBinding.inflate(layoutInflater, parent, false)
        return when (viewType) {
            0 -> FavouriteViewHolder.Base(bindingBase, favouriteClick)
            1 -> FavouriteViewHolder.Empty(bindingEmpty)
            else -> throw IllegalStateException("no data")
        }
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(favouritePost[position])
    }

    override fun getItemCount(): Int = favouritePost.size


    interface FavouriteItemClickListener {
        fun deleteClick(id: Int)
    }

}