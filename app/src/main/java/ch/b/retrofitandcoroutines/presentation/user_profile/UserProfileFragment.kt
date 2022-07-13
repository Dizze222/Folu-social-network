package ch.b.retrofitandcoroutines.presentation.user_profile

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.RouterProvider
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.FragmentUserProfileBinding
import ch.b.retrofitandcoroutines.presentation.core.Ui.CameraFragment
import ch.b.retrofitandcoroutines.presentation.core.*
import ch.b.retrofitandcoroutines.presentation.galary_picker.ImagePickerBottomSheet
import java.util.*
import javax.inject.Inject

class UserProfileFragment :
    BaseFragment<FragmentUserProfileBinding>(FragmentUserProfileBinding::inflate), SharedPhoto {


    @Inject
    lateinit var authenticationViewModelFactory: UserProfileViewModelFactory
    private val viewModel: UserProfileViewModel by viewModels {
        authenticationViewModelFactory
    }

    private var callback: SharedPhoto? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.image.setOnClickListener {
            alertDialog()
        }

        lifecycleScope.launchWhenCreated {
            viewModel.observer(this@UserProfileFragment) {
                it.map { userProfileUi ->
                    userProfileUi.map(
                        binding.name,
                        binding.image,
                        binding.bio,
                        binding.progress,
                        binding.mainLayout
                    )
                    userProfileUi.map(binding.errorLayout, binding.progress, binding.errorTextView)
                }
            }
        }
        viewModel.getUserProfile()
        callback = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }


    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }

    fun inject() {
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }

    private fun alertDialog() {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialog.setMessage("Откуда брать фото?")
        dialog.setTitle("Фото профиля")
        dialog.setPositiveButton("Камера") { _, _ ->
            customCameraFragment()
        }
        dialog.setNegativeButton("Галерея") { _, _ ->
            checkedPermission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        val alertDialog: AlertDialog = dialog.create()
        alertDialog.show()
    }

    private val checkedPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Manifest.permission.READ_EXTERNAL_STORAGE.checkPermission()
            } else {
                val bottomSheetFragment = ImagePickerBottomSheet(this)
                bottomSheetFragment.show(requireActivity().supportFragmentManager, "BFT")

            }
        }

    private fun String.checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), this
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun customCameraFragment() {
        val nextScreen = FragmentScreen(CameraFragment.newInstance())
        (parentFragment as RouterProvider).router.navigateTo(nextScreen)
        setFragmentResultListener("request_key") { _, bundle ->
            val result = bundle.getString("cameraKey")
            val decodedString = Base64.decode(result, Base64.DEFAULT)
            val decodedByte =
                BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            binding.image.setImageBitmap(decodedByte)
            viewModel.sendImage(result!!)
        }
    }

    override fun photo(base64: String) {
        val decodedString = Base64.decode(base64, Base64.DEFAULT)
        val decodedByte =
            BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
        binding.image.setImageBitmap(decodedByte)
        viewModel.sendImage(base64)
    }
}
