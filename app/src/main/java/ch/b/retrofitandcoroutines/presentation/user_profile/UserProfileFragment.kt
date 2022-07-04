package ch.b.retrofitandcoroutines.presentation.user_profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ch.b.retrofitandcoroutines.R


class UserProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }
    companion object{
        fun newInstance() : UserProfileFragment{
            return UserProfileFragment()
        }
    }

}