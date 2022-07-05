package ch.b.retrofitandcoroutines.presentation.user_profile

import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import ch.b.retrofitandcoroutines.databinding.FragmentUserProfileBinding
import ch.b.retrofitandcoroutines.presentation.core.*

class UserProfileFragment :
    BaseFragment<FragmentUserProfileBinding>(FragmentUserProfileBinding::inflate) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imageProfile.setOnClickListener {
            resultLauncher.launch()
        }
        binding.smileOfProfile.text = "\uD83D\uDC7A"
    }


    private var resultLauncher = ActivityResultLauncher.Image(registerForActivityResult(ActivityResultContracts.GetContent()) {
        binding.imageProfile.setImageURI(it)
    })


    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }
}