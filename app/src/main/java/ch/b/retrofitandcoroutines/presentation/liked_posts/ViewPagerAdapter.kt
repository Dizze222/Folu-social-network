package ch.b.retrofitandcoroutines.presentation.liked_posts

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    var tabNames = emptyArray<String>()

    override fun createFragment(position: Int): Fragment {
        return LikedPostsFragment.newInstance(tabNames[position])
    }

    override fun getItemCount(): Int {
        return tabNames.size
    }
}