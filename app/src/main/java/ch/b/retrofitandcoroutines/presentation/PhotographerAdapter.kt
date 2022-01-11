package ch.b.retrofitandcoroutines.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.databinding.PhotographerItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class PhotographerAdapter(private val listener: PhotographerItemClickListener) : RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder>(){

    private val photographers = ArrayList<PhotographerParameters>()

    fun update(new: List<PhotographerParameters>){
        photographers.clear()
        photographers.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotographerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  PhotographerItemBinding.inflate(layoutInflater,parent,false)
        return PhotographerViewHolder(binding,listener)
    }

    override fun onBindViewHolder(holder: PhotographerViewHolder, position: Int)
    = holder.bind(photographers[position])

    override fun getItemCount() = photographers.size

    inner class PhotographerViewHolder(private val binding: PhotographerItemBinding,
    private val listener: PhotographerItemClickListener) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener{
        private lateinit var photographer: PhotographerParameters
        init {
            binding.root.setOnClickListener(this) //TODO fix this
        }
        fun bind(photo: PhotographerParameters){
            binding.apply {
                photographer = photo //TODO fix this
                authorName.text = photo.author
                Glide.with(imageView)
                    .load(photo.URL)
                    .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .placeholder(R.color.colorGrey)
                    .priority(Priority.IMMEDIATE)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
            }
        }

        override fun onClick(v: View?) {
            listener.onClickPhotographer(photographer) //TODO fix this
        }
    }
}