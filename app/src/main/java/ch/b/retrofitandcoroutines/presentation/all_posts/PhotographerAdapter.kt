package ch.b.retrofitandcoroutines.presentation.all_posts

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.*
import ch.b.retrofitandcoroutines.presentation.core.iconAnimation
import ch.b.retrofitandcoroutines.presentation.core.translateY

class PhotographerAdapter(
    private val retry: Retry,
    private val photographerItemClick: PhotographerItemClickListener
) :
    RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder>() {

    private val photographers = ArrayList<PhotographerUI>()


    fun update(new: List<PhotographerUI>) {
        val diffCallback = DiffUtilCallback(photographers, new)
        val result = DiffUtil.calculateDiff(diffCallback)
        photographers.clear()
        photographers.addAll(new)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int) = when (photographers[position]) {
        is PhotographerUI.Base -> 0
        is PhotographerUI.Fail -> 1
        is PhotographerUI.EmptyData -> 2
        else -> 3
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotographerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingBase = PhotographerItemBinding.inflate(layoutInflater, parent, false)
        val bindingFail = FailFullscreenBinding.inflate(layoutInflater, parent, false)
        val bindingProgress = ProgressFullscreenBinding.inflate(layoutInflater, parent, false)
        val bindingEmpty = EmptydataFullscreenBinding.inflate(layoutInflater, parent, false)
        return when (viewType) {
            0 -> PhotographerViewHolder.Base(bindingBase, photographerItemClick)
            1 -> PhotographerViewHolder.Fail(bindingFail, retry)
            2 -> PhotographerViewHolder.EmptyData(bindingEmpty)
            else -> PhotographerViewHolder.FullScreenProgress(bindingProgress)
        }
    }

    override fun onBindViewHolder(holder: PhotographerViewHolder, position: Int) =
        holder.bind(photographers[position])

    override fun getItemCount() = photographers.size

    abstract class PhotographerViewHolder(
        view: ViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        open fun bind(photographer: PhotographerUI) = Unit


        class FullScreenProgress(private val binding: ProgressFullscreenBinding) :
            PhotographerViewHolder(binding) {
            override fun bind(photographer: PhotographerUI) {
                binding.shimmerLayout.startShimmerAnimation()
            }
        }

        class Base(
            private val binding: PhotographerItemBinding,
            private val photographerItemClick: PhotographerItemClickListener
        ) : PhotographerViewHolder(binding) {
            override fun bind(photographer: PhotographerUI) {
                photographer.mapSuccess(
                    binding.authorName,
                    binding.like,
                    binding.imageView,
                    binding.itemPostShowAllComments,
                    binding.someComment
                )
                binding.imageView.setOnClickListener {
                    photographerItemClick.onClickPhotographer(photographer)
                }
                binding.itemPostCollect.setOnClickListener {
                    photographerItemClick.favouriteClick(photographer)
                    it.collectAnimation(photographer)
                }
            }

            private fun View.collectAnimation(photographer: PhotographerUI) {
                photographer.map(binding.itemPostCollectImage)
                binding.itemPostCollect.iconAnimation(
                    R.drawable.bookmark_empty, R.drawable.bookmark
                )
                val dp =
                    if (binding.itemPostCollect.tag == context.getString(R.string.ic_tag_border)) 0 else 38
                binding.itemPostCollection.translateY(dp)


                Handler(Looper.getMainLooper()).postDelayed({
                    binding.itemPostCollection.translateY(38)
                }, 3000)

            }
        }

        class Fail(binding: FailFullscreenBinding, private val retry: Retry) :
            PhotographerViewHolder(binding) {
            private val button = itemView.findViewById<TextView>(R.id.update)
            private val errorTextView = itemView.findViewById<TextView>(R.id.errorTextView)
            override fun bind(photographer: PhotographerUI) {
                photographer.mapError(errorTextView)
                button.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }

        class EmptyData(binding: EmptydataFullscreenBinding) : PhotographerViewHolder(binding)

    }

    interface Retry {
        fun tryAgain()
    }

    interface PhotographerItemClickListener {
        fun onClickPhotographer(photographer: PhotographerUI)
        fun likeClick(photographer: PhotographerUI)
        fun favouriteClick(photographer: PhotographerUI)
    }



}
