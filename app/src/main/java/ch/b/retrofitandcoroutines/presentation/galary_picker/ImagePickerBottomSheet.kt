package ch.b.retrofitandcoroutines.presentation.galary_picker

import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ch.b.retrofitandcoroutines.BackButtonListener
import ch.b.retrofitandcoroutines.RouterProvider
import ch.b.retrofitandcoroutines.core.PhotoApp
import ch.b.retrofitandcoroutines.databinding.BottomSheetImagePickerBinding
import ch.b.retrofitandcoroutines.presentation.core.SharedPhoto
import ch.b.retrofitandcoroutines.presentation.user_profile.core.BaseBottomSheet
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import javax.inject.Inject


class ImagePickerBottomSheet(private val shared: SharedPhoto) : BaseBottomSheet<BottomSheetImagePickerBinding>(),
    BackButtonListener {
    override val bindLayout: (LayoutInflater, ViewGroup?, Boolean) -> BottomSheetImagePickerBinding
        get() = BottomSheetImagePickerBinding::inflate


    @Inject
    lateinit var imagePickerViewModelFactory: ImagePickerViewModelFactory

    private val viewModel: ImagePickerViewModel by viewModels {
        imagePickerViewModelFactory
    }



    private lateinit var bottomSheetBehavior: BottomSheetBehavior<*>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    private val galleryAdapter: GalleryAdapter by lazy {
        GalleryAdapter { image ->
            val imageStream = requireActivity().contentResolver.openInputStream(image!!.mapUri())
            val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
            val stream = ByteArrayOutputStream()
            selectedImage.compress(Bitmap.CompressFormat.PNG, 80, stream)
            val byteArray = stream.toByteArray()
            val base64String: String = Base64.encodeToString(byteArray, Base64.DEFAULT)
            shared.photo(base64String)
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            initBottomSheetDialog(dialog as BottomSheetDialog)
        }
        return dialog
    }

    override fun prepareView(savedInstanceState: Bundle?) {
        viewModel.loadImages()
        initRecyclerView()
        //toolbarBackPressed()
    }

    private fun getScreenHeight(): Int {
        return requireContext().resources.displayMetrics.heightPixels

    }

    private fun initBottomSheetDialog(dialog: BottomSheetDialog) {
        dialog.let {
            val bottomSheet = with(binding.root.rootView) {
                findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            }
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            val density = requireContext().resources.displayMetrics
            bottomSheetBehavior.peekHeight = (density.heightPixels * 0.6).toInt()

            val params = bottomSheet.layoutParams
            params.height = getScreenHeight()
            bottomSheet.layoutParams = params
            bottomSheet.background = requireContext().let {
                ResourcesCompat.getDrawable(
                    resources,
                    ch.b.retrofitandcoroutines.R.drawable.bg_scheet,
                    requireContext().theme
                )
            }
            bottomSheetBehavior.addBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                            binding.bottomSheetLayout.transitionToStart()
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            binding.bottomSheetLayout.transitionToEnd()
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    if (slideOffset > 0) {
                        binding.bottomSheetLayout.progress = slideOffset
                    }
                }
            })
        }
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerGallery.adapter = galleryAdapter
        }
        lifecycleScope.launch {
            viewModel.images.collect {
                galleryAdapter.submitList(it)
            }
        }
    }

    fun inject() {
        val application = requireActivity().application as PhotoApp
        val appComponent = application.appComponent
        appComponent.inject(this)
    }

    override fun onBackPressed(): Boolean {
        (parentFragment as RouterProvider).router.exit()
        return true
    }

}