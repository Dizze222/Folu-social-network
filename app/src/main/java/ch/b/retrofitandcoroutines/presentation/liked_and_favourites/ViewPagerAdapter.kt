package ch.b.retrofitandcoroutines.presentation.liked_and_favourites

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStateAdapter(fragmentManager) {

    val fragmentArrayList: ArrayList<Fragment> = arrayListOf()
    val fragmentTitle: ArrayList<String> = arrayListOf()


    override fun getCount(): Int =
        fragmentArrayList.size

    override fun getItem(position: Int): Fragment =
        fragmentArrayList[position]


    fun addFragment(fragment: Fragment,title: String){
        fragmentArrayList.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle[position]
    }
}