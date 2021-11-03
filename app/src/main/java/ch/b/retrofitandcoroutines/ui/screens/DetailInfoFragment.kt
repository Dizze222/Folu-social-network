package ch.b.retrofitandcoroutines.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FragmentDetailInfoBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class DetailInfoFragment : Fragment() {
  private lateinit var binding: FragmentDetailInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailInfoBinding.bind(view)
        val a = requireArguments().getString(KEY)
        Glide.with(binding.photographerImage)
            .load(a)
            .placeholder(R.drawable.ic_download)
            .circleCrop()
            .into(binding.photographerImage)
    }
    companion object{
        const val KEY = "KEY"
    }

}