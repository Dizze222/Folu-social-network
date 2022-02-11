package ch.b.retrofitandcoroutines.presentation.screens

import android.os.Bundle
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
        val bundle = this.arguments//TODO fix this
        val fragment = PhotographerZoomImageFragment()//TODO fix this
        val photographerId = bundle?.getString("id")//TODO fix this
        val photographerAuthor = bundle?.getString("author")//TODO fix this
        val photographerURL = bundle?.getString("URL")//TODO fix this
        binding.idOfAuthor.text = photographerId//TODO fix this
        binding.author.text = photographerAuthor//TODO fix this
        binding.urlOfAuthor.text = photographerURL//TODO fix this
        bundle!!.putString("URL", photographerURL)//TODO fix this
        fragment.arguments = bundle//TODO fix this
        binding.toolbar.title = photographerAuthor


        binding.imageOfAuthor.setOnClickListener{
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
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