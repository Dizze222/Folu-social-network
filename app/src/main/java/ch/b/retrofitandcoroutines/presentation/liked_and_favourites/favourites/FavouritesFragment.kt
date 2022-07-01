package ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.FragmentFavouritesBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.stories.StoriesContainerAdapter
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import javax.inject.Inject


class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {

    @Inject
    lateinit var favouriteViewModelFactory: FavouriteViewModelFactory
    private val favouriteViewModel: FavouriteViewModel by viewModels {
        favouriteViewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FavouriteAdapter()
        binding.listOfFavourites.adapter = adapter
        binding.listOfFavourites.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        lifecycleScope.launchWhenStarted {
            favouriteViewModel.observe(this@FavouritesFragment,{
                adapter.update(it)
            })
        }
        favouriteViewModel.getFavouritePost()
    }
    companion object{
        fun newInstance() : FavouritesFragment{
            return FavouritesFragment()
        }
    }
    fun inject(){
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }
}