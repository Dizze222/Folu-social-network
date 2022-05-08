package ch.b.retrofitandcoroutines.presentation.certain_post

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographerDetailBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.PhotographerUI
import android.net.wifi.WifiManager
import android.text.format.Formatter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.presentation.screens.PhotographerZoomImageFragment


class PhotographerDetailFragment : Fragment() {
    private lateinit var binding: FragmentPhotographerDetailBinding
    //private lateinit var certainViewModel: CertainPostViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotographerDetailBinding.bind(view)
       // certainViewModel = (activity?.application as PhotographerApp).certainPost

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photographer_detail, container, false)
    }
}

fun convertToArrayList(list: List<String>) : ArrayList<String>{
    val someList = arrayListOf<String>()
    for (i in list){
        someList.add(i)
    }
    return someList
}