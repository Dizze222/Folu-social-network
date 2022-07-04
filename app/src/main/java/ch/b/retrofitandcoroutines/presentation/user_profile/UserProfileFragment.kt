package ch.b.retrofitandcoroutines.presentation.user_profile

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ch.b.retrofitandcoroutines.databinding.FragmentUserProfileBinding
import ch.b.retrofitandcoroutines.presentation.core.*

class UserProfileFragment : Fragment(), ImageResult {
    private var imageProfile:ImageProfile = ImageProfile.Empty
    private lateinit var binding: FragmentUserProfileBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.imageProfile.setOnClickListener{
            (requireActivity() as ActivityLauncher).launch(LauncherType.Image)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentUserProfileBinding.inflate(layoutInflater)
    }
    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }

    override fun onImageResult(uri: Uri) {
        binding.imageProfile.setImageURI(uri)
    }
}