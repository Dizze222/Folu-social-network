package ch.b.retrofitandcoroutines.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.databinding.GridItemBinding
import com.bumptech.glide.Glide

class MainAdapter(private val userDTO: ArrayList<UserDTO>, val adapterOnClick: AdapterOnClick) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {


    inner class DataViewHolder(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserDTO) {
            binding.apply {
                authorName.text = data.authorOfPicture
                IdOfPicture.text = data.ID
                Glide.with(imageView)
                    .load(data.downloadedPicture)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageView)
            }
        }
        fun onClickItem(item: UserDTO) {
            binding.imageView.setOnClickListener {
                adapterOnClick.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = GridItemBinding.inflate(layoutInflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userDTO[position])
        holder.onClickItem(userDTO[position])
    }

    override fun getItemCount(): Int = userDTO.size

    fun addUsers(userDTOS: List<UserDTO>) {
        this.userDTO.apply {
            clear()
            addAll(userDTOS)
            notifyDataSetChanged()
        }
    }

}


