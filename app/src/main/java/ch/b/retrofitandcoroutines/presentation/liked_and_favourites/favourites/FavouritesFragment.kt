package ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FragmentFavouritesBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.stories.StoriesContainerAdapter
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import javax.inject.Inject


class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate) {
    @Inject
    lateinit var favouriteViewModelFactory: FavouriteViewModelFactory
    private lateinit var storiesContainerAdapter: StoriesContainerAdapter
    private val favouriteViewModel: FavouriteViewModel by viewModels {
        favouriteViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }

    companion object{
        fun newInstance() : FavouritesFragment{
            return FavouritesFragment()
        }
    }



}