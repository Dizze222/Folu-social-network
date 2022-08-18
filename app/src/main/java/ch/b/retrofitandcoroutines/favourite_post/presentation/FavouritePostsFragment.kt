package ch.b.retrofitandcoroutines.favourite_post.presentation

import android.os.Bundle
import android.view.View
import ch.b.retrofitandcoroutines.databinding.FragmentFavouriteBinding
import ch.b.retrofitandcoroutines.utils.core_ui.BaseFragment
import ch.b.retrofitandcoroutines.favourite_post.presentation.core.FavouriteNames
import ch.b.retrofitandcoroutines.favourite_post.presentation.favourites.FavouritesFragment
import ch.b.retrofitandcoroutines.favourite_post.presentation.liked.LikedFragment
import com.google.android.material.tabs.TabLayoutMediator

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
