package ch.b.retrofitandcoroutines.presentation.user_profile

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import android.widget.TextView
import ch.b.retrofitandcoroutines.R
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.user_profile.core.BaseUserProfileStringMapper

sealed class UserProfileUi : Abstract.Object<Unit, BaseUserProfileStringMapper.SingleStringMapper> {

    open fun map(textView: TextView, imageView: ImageView, bioTextView: TextView) = Unit


    class Base(
        private val name: String,
        private val secondName: String,
        private val bio: String,
        private val image: String
    ) : UserProfileUi() {
        override fun map(mapper: BaseUserProfileStringMapper.SingleStringMapper) {
            mapper.map(name, secondName, bio, image)
        }

        @SuppressLint("SetTextI18n")
        override fun map(textView: TextView, imageView: ImageView, bioTextView: TextView) {
            textView.text = name + secondName
            bioTextView.text = bio
            if (image == "empty") {
                imageView.setImageResource(R.drawable.ic_collect)
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

    }
}