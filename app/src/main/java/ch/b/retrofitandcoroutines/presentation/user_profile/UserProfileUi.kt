package ch.b.retrofitandcoroutines.presentation.user_profile

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.user_profile.core.BaseUserProfileStringMapper

sealed class UserProfileUi : Abstract.Object<Unit, BaseUserProfileStringMapper.SingleStringMapper> {

    open fun map(
        textView: TextView,
        imageView: ImageView,
        bioTextView: TextView,
        progress: ProgressBar,
        mainLayout: LinearLayout
    ) = Unit
    open fun map(mainView: LinearLayout, progress: View) = Unit
    open fun map(errorLayout: LinearLayout,progress: ProgressBar,textView: TextView) = Unit
    class Base(
        private val fullName: String,
        private val bio: String,
        private val image: String
    ) : UserProfileUi() {
        override fun map(mapper: BaseUserProfileStringMapper.SingleStringMapper) {
            mapper.map(fullName, bio, image)
        }

        @SuppressLint("SetTextI18n")
        override fun map(
            textView: TextView,
            imageView: ImageView,
            bioTextView: TextView,
            progress: ProgressBar,
            mainLayout: LinearLayout
        ) {
            mainLayout.visibility = View.VISIBLE
            progress.visibility = View.GONE
            textView.text = fullName
            bioTextView.text = bio
            if (image == "empty") {
                imageView.setImageResource(R.drawable.ic_user_profile)
            } else {
                val decodedString = Base64.decode(image, Base64.DEFAULT)
                val decodedByte =
                    BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                imageView.setImageBitmap(decodedByte)
            }
        }
    }

    class Fail(private val message: String) : UserProfileUi() {
        override fun map(mapper: BaseUserProfileStringMapper.SingleStringMapper) {
            mapper.map(message)
        }

        override fun map(errorLayout: LinearLayout, progress: ProgressBar, textView: TextView) {
            errorLayout.visibility = View.VISIBLE
            progress.visibility = View.GONE
            textView.text = message
        }
    }

    object Progress : UserProfileUi() {
        override fun map(mapper: BaseUserProfileStringMapper.SingleStringMapper) {
            mapper.map(true)
        }

        override fun map(mainView: LinearLayout, progress: View) {
            mainView.visibility = View.GONE
            progress.visibility = View.VISIBLE
            Log.i("TEST","PROGRESS")
        }
    }


}