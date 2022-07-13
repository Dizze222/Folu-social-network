package ch.b.retrofitandcoroutines.presentation.user_search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.b.retrofitandcoroutines.BackButtonListener
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.RouterProvider


class UserSearchFragment : Fragment(),BackButtonListener {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user_search, container, false)
    }

    fun newInstance(): Fragment {
        return UserSearchFragment()
    }

    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }


}