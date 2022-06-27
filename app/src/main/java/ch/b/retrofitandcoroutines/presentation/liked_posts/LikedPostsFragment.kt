package ch.b.retrofitandcoroutines.presentation.liked_posts

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.databinding.FragmentLikedBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class LikedPostsFragment : BaseFragment<FragmentLikedBinding>(FragmentLikedBinding::inflate) {


    private var adapter: ViewPagerAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
    }

    companion object {
        private val ARGS_KEY_TAB_NAME = "ARGS_KEY_TAB_NAME"
        fun newInstance(tabName: String): LikedPostsFragment {
            val args = Bundle()
            args.putString(ARGS_KEY_TAB_NAME, tabName)
            val fragment = LikedPostsFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance(): LikedPostsFragment {
            return LikedPostsFragment()
        }
    }
    private fun setUpViewPager() {
        adapter = ViewPagerAdapter(this)
        adapter!!.tabNames = arrayOf("one", "two")
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tab, binding.viewPager) { tab, position ->
            tab.contentDescription = "$position"
        }.attach()
    }

    private fun namesOfFragment(index: Int): String {
        val arrayOfNamesFragment = arrayOf("one", "two")
        return arrayOfNamesFragment[index]
    }
}