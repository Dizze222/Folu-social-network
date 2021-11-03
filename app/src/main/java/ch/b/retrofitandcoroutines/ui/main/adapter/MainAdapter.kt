package ch.b.retrofitandcoroutines.ui.main.adapter


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.data.model.UserDTO
import ch.b.retrofitandcoroutines.databinding.GridItemBinding
import com.bumptech.glide.Glide
import java.util.*

class MainAdapter(private val userDTO: ArrayList<UserDTO>, val adapterOnClick: AdapterOnClick) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    var isShimmer = true


    inner class DataViewHolder(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: UserDTO) {
            binding.apply {
                if (isShimmer){
                    binding.shimmerLayout.startShimmerAnimation()
                    }else if (!isShimmer) {
                    authorName.text = data.authorOfPicture
                    IdOfPicture.text = data.ID
                    Glide.with(imageView)
                        .load(data.downloadedPicture)
                        .placeholder(R.drawable.ic_download)
                        .into(imageView)
                }
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
        Log.i("TAG","On Create View holder")
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(userDTO[position])
        holder.onClickItem(userDTO[position])
        Log.i("TAG",position.toString())

    }

    override fun getItemCount() = userDTO.size

    @SuppressLint("NotifyDataSetChanged")
    fun addUsers(userDTOS: List<UserDTO>) {
        this.userDTO.apply {
            clear()
            addAll(userDTOS)
            notifyDataSetChanged()
        }
    }

}


