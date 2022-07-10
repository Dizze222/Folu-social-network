package ch.b.retrofitandcoroutines.presentation.containers

import android.os.Bundle
import ch.b.retrofitandcoroutines.FragmentScreen
import ch.b.retrofitandcoroutines.presentation.user_profile.galary_picker.ImagePickerScreen

class PicContainer : BaseFragmentContainer() {

    companion object {
        const val TAG = "PicTabContainerTag"
        fun newInstance() : PicContainer{
            return PicContainer()
        }
    }

    override fun getInitialFragmentScreen(params: Bundle?): FragmentScreen {
        return FragmentScreen(ImagePickerScreen.newInstance())
    }
}