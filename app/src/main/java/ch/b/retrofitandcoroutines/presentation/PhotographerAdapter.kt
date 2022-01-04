package ch.b.retrofitandcoroutines.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.PhotographerParameters

class PhotographerAdapter : RecyclerView.Adapter<PhotographerAdapter.PhotographerViewHolder>(){

    private val photographers = ArrayList<PhotographerParameters>()

    fun update(new: List<PhotographerParameters>){
        photographers.clear()
        photographers.addAll(new)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotographerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photographer_item,parent,false)
        return PhotographerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotographerViewHolder, position: Int)
    = holder.bind(photographers[position])

    override fun getItemCount() = photographers.size

    inner class PhotographerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bind(photo: PhotographerParameters){
            itemView.findViewById<TextView>(R.id.textView).text = photo.author
        }
    }
}