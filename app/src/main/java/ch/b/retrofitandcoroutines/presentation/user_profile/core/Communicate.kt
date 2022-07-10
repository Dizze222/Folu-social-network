package ch.b.retrofitandcoroutines.presentation.user_profile.core

import android.os.Parcelable
import androidx.fragment.app.Fragment

interface Communicate {

    fun showPickerSheet(fragment: Fragment)
    fun selectImage(fragment: Fragment, imageData: Parcelable?)

    class Base() : Communicate{
        override fun showPickerSheet(fragment: Fragment) {

        }

        override fun selectImage(fragment: Fragment, imageData: Parcelable?) {
        }

    }
}

const val PASSED_DATA = "passed data"
val Fragment.navigationData: Parcelable?
    get() = arguments?.getParcelable(PASSED_DATA)
