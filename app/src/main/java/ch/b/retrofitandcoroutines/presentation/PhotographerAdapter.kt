package ch.b.retrofitandcoroutines.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.PhotographerParameters
import ch.b.retrofitandcoroutines.databinding.PhotographerItemBinding
import com.bumptech.glide.Glide

class PhotographerAdapter : RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder>(){

    private val photographers = ArrayList<PhotographerParameters>()

    fun update(new: List<PhotographerParameters>){
        photographers.clear()
        photographers.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotographerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =  PhotographerItemBinding.inflate(layoutInflater,parent,false)
        return PhotographerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotographerViewHolder, position: Int)
    = holder.bind(photographers[position])

    override fun getItemCount() = photographers.size

    inner class PhotographerViewHolder(private val binding: PhotographerItemBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(photo: PhotographerParameters){
            binding.apply {
                Log.i("TAG",photo.URL)
                Log.i("TAG",photo.author)
                authorName.text = photo.author
                Glide.with(imageView)
                    .load(photo.URL)
                    .placeholder(R.drawable.ic_download)
                    .error(R.drawable.ic_launcher_background)
                    .into(imageView)
            }
        }
    }
}