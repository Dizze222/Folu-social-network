package ch.b.retrofitandcoroutines.presentation.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import ch.b.retrofitandcoroutines.core.PhotographerApp
import ch.b.retrofitandcoroutines.databinding.FragmentPhotographersBinding
import ch.b.retrofitandcoroutines.presentation.PhotographerAdapter
import ch.b.retrofitandcoroutines.presentation.PhotographerItemClickListener
import androidx.fragment.app.FragmentManager
import ch.b.retrofitandcoroutines.core.PhotographerParameters


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
        return inflater.inflate(ch.b.retrofitandcoroutines.R.layout.fragment_photographers, container, false)
    }

    override fun onClickPhotographer(photographer: PhotographerParameters) { //TODO fix this
        Toast.makeText(activity,photographer.id.toString(),Toast.LENGTH_LONG).show()
        val fragment = PhotographerDetailFragment()
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(ch.b.retrofitandcoroutines.R.id.fragmentContainer, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()

        val bundle = Bundle()//TODO fix this
        bundle.putString("id", photographer.id.toString())//TODO fix this
        bundle.putString("author",photographer.author)//TODO fix this
        bundle.putString("URL",photographer.URL)//TODO fix this
        fragment.arguments = bundle//TODO fix this



    }
}