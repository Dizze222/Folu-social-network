package ch.b.retrofitandcoroutines.presentation


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FailFullscreenBinding
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import ch.b.retrofitandcoroutines.databinding.PhotographerItemBinding
import ch.b.retrofitandcoroutines.databinding.ProgressFullscreenBinding


class PhotographerAdapter(private val retry: Retry) :
    RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder>() {

    private val photographers = ArrayList<PhotographerUI>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(new: List<PhotographerUI>) {
        photographers.clear()
        photographers.addAll(new)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (photographers[position]) {
        is PhotographerUI.Base -> 0
        is PhotographerUI.Fail -> 1
        else -> 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotographerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindingBase = PhotographerItemBinding.inflate(layoutInflater, parent, false)
        val bindingFail = FailFullscreenBinding.inflate(layoutInflater, parent, false)
        val bindingProgress = ProgressFullscreenBinding.inflate(layoutInflater, parent, false)
        return when (viewType) {
            0 -> PhotographerViewHolder.Base(bindingBase)
            1 -> PhotographerViewHolder.Fail(bindingFail, retry)
            else -> PhotographerViewHolder.FullScreenProgress(bindingProgress)
        }
    }

    override fun onBindViewHolder(holder: PhotographerViewHolder, position: Int) =
        holder.bind(photographers[position])

    override fun getItemCount() = photographers.size

    abstract class PhotographerViewHolder(
        view: ViewBinding,
    ) : RecyclerView.ViewHolder(view.root) {
        open fun bind(photographer: PhotographerUI) {}


        class FullScreenProgress(view: ProgressFullscreenBinding) : PhotographerViewHolder(view)

        class Base(private val view: PhotographerItemBinding) : PhotographerViewHolder(view) {
            override fun bind(photographer: PhotographerUI) {
                photographer.map(object : PhotographerUI.StringMapper {
                    override fun map(
                        id: Int,
                        author: String,
                        URL: String,
                        like: Long,
                        theme: String
                    ) {
                        view.authorName.text = author
                        view.like.text = like.toString()
                    }

                    override fun map(message: String) {
                        Log.i("TAG", message)
                    }

                })
            }

        }

        class Fail(view: FailFullscreenBinding, private val retry: Retry) :
            PhotographerViewHolder(view) {
            private val button = itemView.findViewById<TextView>(R.id.update)
            override fun bind(photographer: PhotographerUI) {
                photographer.map(object : PhotographerUI.StringMapper {
                    override fun map(
                        id: Int,
                        author: String,
                        URL: String,
                        like: Long,
                        theme: String
                    ) {
                        Log.i("TAG", id.toString())
                    }

                    override fun map(message: String) {

                    }
                })
                button.setOnClickListener {
                    retry.tryAgain()
                }
            }
        }

    }

    interface Retry {
        fun tryAgain()
    }
}
