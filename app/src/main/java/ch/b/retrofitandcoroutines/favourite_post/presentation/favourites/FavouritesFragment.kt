package ch.b.retrofitandcoroutines.favourite_post.presentation.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ch.b.retrofitandcoroutines.BackButtonListener
import ch.b.retrofitandcoroutines.RouterProvider
import ch.b.retrofitandcoroutines.utils.core.PhotoApp
import ch.b.retrofitandcoroutines.utils.core.Reader
import ch.b.retrofitandcoroutines.databinding.FragmentFavouritesBinding
import ch.b.retrofitandcoroutines.utils.core_ui.BaseFragment
import ch.b.retrofitandcoroutines.utils.core_ui.SharedPreferencesFavourite
import javax.inject.Inject

class FavouritesFragment :
    BaseFragment<FragmentFavouritesBinding>(FragmentFavouritesBinding::inflate),
    BackButtonListener {


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
        val preferences = SharedPreferencesFavourite.Base(requireContext(), Reader())
        val itemCollectClickListener = object : FavouriteAdapter.FavouriteItemClickListener {
            val localId = preferences.readIdOfFavouritePost()
            override fun deleteClick(id: Int) {
                val newId = localId.replace(id.toString(), " ")
                favouriteViewModel.deleteFavouritePost(id)
                preferences.saveFavouritePost(newId)
                favouriteViewModel.getFavouritePost()
            }
        }
        val adapter = FavouriteAdapter(itemCollectClickListener)
        binding.listOfFavourites.adapter = adapter
        binding.listOfFavourites.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        lifecycleScope.launchWhenStarted {
            favouriteViewModel.observe(this@FavouritesFragment, {
                adapter.update(it)
            })
        }
        favouriteViewModel.getFavouritePost()
    }

    companion object {
        fun newInstance(): FavouritesFragment {
            return FavouritesFragment()
        }
    }

    fun inject() {
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)

    }

    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }
}