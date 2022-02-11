package ch.b.retrofitandcoroutines.presentation


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R


class PhotographerAdapter(private val retry: Retry) :
    RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder>() {


    private val photographers = ArrayList<PhotographerUI>()

    @SuppressLint("NotifyDataSetChanged")
    fun update(new: List<PhotographerUI>) {
        photographers.clear()
        photographers.addAll(new)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (photographers[position]) {
            is PhotographerUI.Base -> 0
            is PhotographerUI.Fail -> 1
            else -> 2
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when(viewType){
        0 -> PhotographerViewHolder.Base(R.layout.photographer_item.makeView(parent))
        1 -> PhotographerViewHolder.Fail(R.layout.fail_fullscreen.makeView(parent),retry)
        else -> PhotographerViewHolder.FullScreenProgress(R.layout.progress_fullscreen.makeView(parent))
    }

    override fun onBindViewHolder(holder: PhotographerViewHolder, position: Int) =
        holder.bind(photographers[position])

    override fun getItemCount() = photographers.size

    abstract class PhotographerViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        open fun bind(photographer: PhotographerUI) {}


        class FullScreenProgress(view: View) : PhotographerViewHolder(view)

        class Base(view: View) : PhotographerViewHolder(view) {
            private val authorText = itemView.findViewById<TextView>(R.id.authorName)
            override fun bind(photographer: PhotographerUI) {
                photographer.map(object : PhotographerUI.StringMapper {
                    override fun map(
                        id: Int,
                        author: String,
                        URL: String,
                        like: Long,
                        theme: String
                    ) {
                        authorText.text = author
                    }

                    override fun map(message: String) {
                        Log.i("TAG", message)
                    }

                })
            }

        }

        class Fail(view: View, private val retry: Retry) : PhotographerViewHolder(view) {
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
private fun Int.makeView(parent: ViewGroup) =
    LayoutInflater.from(parent.context).inflate(this, parent, false)