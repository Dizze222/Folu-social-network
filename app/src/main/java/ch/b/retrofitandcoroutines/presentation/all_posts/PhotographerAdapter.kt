package ch.b.retrofitandcoroutines.presentation.all_posts


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.ImageLoad
import ch.b.retrofitandcoroutines.databinding.*



class PhotographerAdapter(
    private val retry: Retry,
    private val photographerItemClick: PhotographerItemClickListener
) :
    RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder>() {

    private val photographers = ArrayList<PhotographerUI>()

    @SuppressLint("NotifyDataSetChanged")
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
        //is PhotographerUI.EmptyData -> 2
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
           // 2 -> PhotographerViewHolder.EmptyData(bindingEmpty)
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
                photographer.map(object : PhotographerUI.StringMapper {
                    override fun map(
                        id: Int,
                        author: String,
                        URL: String,
                        like: Long,
                        theme: String,
                        comments: List<String>,
                        authorOfComments: List<String>
                    ) {
                        binding.authorName.text = author
                        binding.like.text = like.toString()
                        ImageLoad.Base(URL).load(binding.imageView)
                    }

                    override fun map(message: String) = Unit


                })
                binding.imageView.setOnClickListener {
                    photographerItemClick.onClickPhotographer(photographer)
                }
            }

        }

        class Fail(binding: FailFullscreenBinding, private val retry: Retry) :
            PhotographerViewHolder(binding) {
            private val button = itemView.findViewById<TextView>(R.id.update)
            override fun bind(photographer: PhotographerUI) {
                photographer.map(object : PhotographerUI.StringMapper {
                    override fun map(
                        id: Int,
                        author: String,
                        URL: String,
                        like: Long,
                        theme: String,
                        comments: List<String>,
                        authorOfComments: List<String>

                    ) {
                        Log.i("TAG", id.toString())
                    }

                    override fun map(message: String) = Unit
                })
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
    }
}
