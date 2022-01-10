package ch.b.retrofitandcoroutines.presentation.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.MainActivity
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.PhotographerApp
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import ch.b.retrofitandcoroutines.presentation.PhotographerAdapter
import ch.b.retrofitandcoroutines.presentation.PhotographerItemClickListener


class PhotographersFragment : Fragment(),PhotographerItemClickListener {

    private lateinit var binding: FragmentPhotographersBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotographersBinding.bind(view)
        val viewModel = (activity?.application as PhotographerApp).mainViewModel
        val adapter = PhotographerAdapter(this) //TODO fix this
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(activity,2)
        viewModel.observe(this,{
            adapter.update(it)
        })
        viewModel.getPhotographers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photographers, container, false)
    }

    override fun onClickPhotographer(photographerId: Int) { //TODO fix this
        Toast.makeText(activity,photographerId.toString(),Toast.LENGTH_LONG).show() //TODO fix this

    }
}