package ch.b.retrofitandcoroutines.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.databinding.ItemLayoutBinding

import com.bumptech.glide.Glide

class MainAdapter(private val userDTOS: ArrayList<UserDTO>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserDTO) {
            binding.apply {
                binding.textViewUserName.text = data.userName
                binding.textViewUserEmail.text = data.userEmail
                Glide.with(imageViewAvatar)
                    .load(data.avatarImage)
                    .circleCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(imageViewAvatar)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(layoutInflater,parent,false)
        return DataViewHolder(binding)
    }
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userDTOS[position])
    }

    override fun getItemCount(): Int = userDTOS.size

    fun addUsers(userDTOS: List<UserDTO>) {
        this.userDTOS.apply {
            clear()
            addAll(userDTOS)
        }

    }


}


