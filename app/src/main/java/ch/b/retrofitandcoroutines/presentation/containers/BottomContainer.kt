package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.user_profile.galary_picker.ImagePickerBottomSheet


class BottomContainer : BaseFragmentContainer() {

    companion object {
        const val TAG = "BottomTabContainerTag"
        fun newInstance() : BottomContainer{
            return BottomContainer()
        }
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(ImagePickerBottomSheet.newInstance())
    }
}