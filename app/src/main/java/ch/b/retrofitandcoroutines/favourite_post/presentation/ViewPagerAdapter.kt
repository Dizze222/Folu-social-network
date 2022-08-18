package ch.b.retrofitandcoroutines.favourite_post.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val fragmentArrayList: ArrayList<Fragment> = arrayListOf()


    fun addFragment(fragment: Fragment) {
        fragmentArrayList.add(fragment)
    }

    override fun getItemCount(): Int =
        fragmentArrayList.size

    override fun createFragment(position: Int): Fragment =
        fragmentArrayList[position]
}