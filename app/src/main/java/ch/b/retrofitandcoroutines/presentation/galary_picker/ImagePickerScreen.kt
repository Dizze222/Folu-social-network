package ch.b.retrofitandcoroutines.presentation.galary_picker

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.RouterProvider
import ch.b.retrofitandcoroutines.databinding.ScreenPickerImageBinding
import ch.b.retrofitandcoroutines.presentation.core.BaseFragment
import ch.b.retrofitandcoroutines.presentation.user_profile.core.navigationData

class ImagePickerScreen : BaseFragment<ScreenPickerImageBinding>(ScreenPickerImageBinding::inflate){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareView()

    }

    private fun prepareView() {
        initClicks()
        //setImage(binding.image)
    }

    private fun initClicks() {
        with(binding) {
            btnPickImage.setOnClickListener(clickListener)
        }
    }

    private val clickListener = View.OnClickListener {
        with(binding) {
            when (it.id) {
                btnPickImage.id -> checkedPermission
                    .launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun setImage(image: ImageView) {
        val data = navigationData as? MediaStoreImage
        image.setImageURI(data!!.mapUri())
    }

    private fun clearImage(image: ImageView) {
        image.setImageDrawable(
            AppCompatResources.getDrawable(
                requireContext(),
                R.drawable.ic_user_profile
            )
        )
    }

    private val checkedPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Manifest.permission.READ_EXTERNAL_STORAGE.checkPermission()
            } else {
                val nextScreen = FragmentScreen(ImagePickerBottomSheet.newInstance())
                (parentFragment as RouterProvider).router.navigateTo(nextScreen)
            }
        }

    private fun String.checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(), this
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object{
        fun newInstance() : ImagePickerScreen{
            return ImagePickerScreen()
        }
    }
}