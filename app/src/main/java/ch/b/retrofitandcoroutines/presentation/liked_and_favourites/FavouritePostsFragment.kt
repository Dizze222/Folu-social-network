package ch.b.retrofitandcoroutines.presentation.liked_and_favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentPagerAdapter
import ch.b.retrofitandcoroutines.databinding.FragmentFavouriteBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.favourites.FavouritesFragment
import ch.b.retrofitandcoroutines.presentation.liked_and_favourites.liked.LikedFragment

class FavouritePostsFragment : BaseFragment<FragmentFavouriteBinding>(FragmentFavouriteBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewPagerAdapter = ViewPagerAdapter(fragmentManager!!,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        viewPagerAdapter.addFragment(FavouritesFragment.newInstance(),"Избранное")
        viewPagerAdapter.addFragment(LikedFragment.newInstance(),"Лайки")
        binding.viewPager.adapter = viewPagerAdapter
    }

    companion object {
        private val ARGS_KEY_TAB_NAME = "ARGS_KEY_TAB_NAME"
        fun newInstance(tabName: String): FavouritePostsFragment {
            val args = Bundle()
            args.putString(ARGS_KEY_TAB_NAME, tabName)
            val fragment = FavouritePostsFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance(): FavouritePostsFragment {
            return FavouritePostsFragment()
        }
    }

}