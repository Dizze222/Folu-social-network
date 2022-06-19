package ch.b.retrofitandcoroutines.presentation.all_posts

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.BasePhotographerStringMapper
import ch.b.retrofitandcoroutines.presentation.core.ImageLoad


sealed class PhotographerUI :
    Abstract.Object<Unit, BasePhotographerStringMapper.SingleStringMapper>, Comparing {
    override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) = Unit
    open fun map(mapper: BasePhotographerStringMapper.IdMapper) = Unit

    open fun mapSuccess(authorView: TextView, like: TextView,imageView: ImageView,comment: TextView) = Unit

    open fun mapError(errorTextView: TextView) = Unit

    object Progress : PhotographerUI() {
        override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) =
            mapper.map(true)
    }

    object EmptyData : PhotographerUI()

    class Base(
        private val id: Int,
        private val author: String,
        private val URL: String,
        private val like: Long,
        private val theme: String,
        private val comments: List<String>,
        private val authorOfComments: List<String>
    ) : PhotographerUI() {
        override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) =
            mapper.map(id, author, URL, like, theme, comments, authorOfComments)

        override fun map(mapper: BasePhotographerStringMapper.IdMapper){
            mapper.map(id)
        }
        @SuppressLint("SetTextI18n")
        override fun mapSuccess(
            authorView: TextView,
            like: TextView,
            imageView: ImageView,
            comment: TextView
        ) {
            authorView.text = author
            like.text = this.like.toString()
            comment.text = "Показать более ${authorOfComments.size - 1} комментария"
            ImageLoad.Base(URL).load(imageView)
        }

        override fun same(photographerUI: PhotographerUI) =
            photographerUI is Base && id == photographerUI.id

        override fun sameContent(photographerUI: PhotographerUI) =
            photographerUI is Base && id == photographerUI.id
    }

    class Fail(private val message: String) : PhotographerUI() {
        override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) =
            mapper.map(message)

        override fun mapError(errorTextView: TextView) {
            errorTextView.text = message
        }
    }

}

interface Comparing {
    fun sameContent(photographerUI: PhotographerUI) = false
    fun same(photographerUI: PhotographerUI) = false
}