package ch.b.retrofitandcoroutines.presentation.liked_and_favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ch.b.retrofitandcoroutines.databinding.FragmentFavouriteBinding
import ch.b.retrofitandcoroutines.presentation.all_posts.AllPostsViewModel
import ch.b.retrofitandcoroutines.presentation.all_posts.AllPostsViewModelFactory
import ch.b.retrofitandcoroutines.presentation.all_posts.stories.StoriesContainerAdapter
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.core.FavouriteNames
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites.FavouriteViewModel
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites.FavouriteViewModelFactory
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites.FavouritesFragment
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.liked.LikedFragment
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject

class FavouritePostsFragment :
    BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(FavouritesFragment.newInstance())
        viewPagerAdapter.addFragment(LikedFragment.newInstance())
        binding.viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = FavouriteNames.namesOfTab(requireContext(),position)
        }.attach()
    }

    companion object {
        fun newInstance(): FavouritePostsFragment {
            return FavouritePostsFragment()
        }
    }

}
