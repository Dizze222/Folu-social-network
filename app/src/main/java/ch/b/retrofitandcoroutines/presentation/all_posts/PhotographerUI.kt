package ch.b.retrofitandcoroutines.presentation.all_posts

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.presentation.all_posts.core.BasePhotographerStringMapper
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain
import ch.b.retrofitandcoroutines.presentation.core.ImageLoad

sealed class PhotographerUI :
    Abstract.Object<Unit, BasePhotographerStringMapper.SingleStringMapper>, Comparing {
    override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) = Unit
    open fun map(mapper: BasePhotographerStringMapper.IdMapper) = Unit
    open fun map(imageView: ImageView) = Unit
    open fun mapSuccess(
        authorView: TextView,
        like: TextView,
        imageView: ImageView,
        comment: TextView,
        someComment: TextView
    ) = Unit
    open fun mapFlag(): Boolean = false
    open fun checkFavourite(): Boolean = true
    open fun mapError(errorTextView: TextView) = Unit
    open fun list(): List<PhotographerDomain> = listOf()
    open fun map(): String = ""
    open fun mapId(): Int = -1


    object Progress : PhotographerUI() {
        override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) =
            mapper.map(true)
    }

    object EmptyData : PhotographerUI()

    data class Base(
        private val id: Int,
        private val author: String,
        private val URL: String,
        private val like: Long,
        private val theme: String,
        private val comments: List<String>,
        private val authorOfComments: List<String>,
        private var favourite: Boolean
    ) : PhotographerUI() {
        override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) =
            mapper.map(id, author, URL, like, theme, comments, authorOfComments)

        override fun mapId(): Int {
            return id
        }

        override fun map(): String {
            return id.toString()
        }

        override fun mapFlag(): Boolean {
            return favourite
        }
        override fun list(): List<PhotographerDomain> =
            arrayListOf(
                PhotographerDomain.Base(
                    id,
                    author,
                    URL,
                    like,
                    theme,
                    comments,
                    authorOfComments,
                    favourite = true
                )
            )

        override fun map(mapper: BasePhotographerStringMapper.IdMapper) = mapper.map(id)
        override fun map(imageView: ImageView) {
            ImageLoad.Base(URL).load(imageView)
        }

        override fun checkFavourite(): Boolean {
            return favourite
        }

        @SuppressLint("SetTextI18n")
        override fun mapSuccess(
            authorView: TextView,
            like: TextView,
            imageView: ImageView,
            comment: TextView,
            someComment: TextView
        ) {
            authorView.text = author
            like.text = "Нравится: ${this.like}"
            comment.text = "Показать все комментарии (${this.comments.size})"
            someComment.text = "${authorOfComments[0]}: ${this.comments[0]}"
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