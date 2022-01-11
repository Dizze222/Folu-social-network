package ch.b.retrofitandcoroutines.presentation.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographerDetailBinding
import com.bumptech.glide.Glide


class PhotographerDetailFragment : Fragment() {
    private lateinit var binding: FragmentPhotographerDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotographerDetailBinding.bind(view)
        val bundle = this.arguments
        val fragment = FullImageView()
        val photographerId = bundle?.getString("id")
        val photographerAuthor = bundle?.getString("author")
        val photographerURL = bundle?.getString("URL")
        Log.i("TAG",photographerId.toString())
        binding.idOfAuthor.text = photographerId
        binding.author.text = photographerAuthor
        binding.urlOfAuthor.text = photographerURL
        Glide.with(binding.imageOfAuthor)
            .load(photographerURL)
            .into(binding.imageOfAuthor)
        bundle!!.putString("URL", photographerURL)
        fragment.arguments = bundle



        binding.imageOfAuthor.setOnClickListener{
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photographer_detail, container, false)
    }

}