package ch.b.retrofitandcoroutines.presentation.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.core.BasePhotographerStringMapper


sealed class PhotographerUI :
    Abstract.Object<Unit, BasePhotographerStringMapper.SingleStringMapper>, Comparing {
    override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) = Unit

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

        override fun same(photographerUI: PhotographerUI) =
            photographerUI is Base && id == photographerUI.id

        override fun sameContent(photographerUI: PhotographerUI) =
            photographerUI is Base && id == photographerUI.id
    }

    class Fail(private val message: String) : PhotographerUI() {
        override fun map(mapper: BasePhotographerStringMapper.SingleStringMapper) =
            mapper.map(message)
    }

}

interface Comparing {
    fun sameContent(photographerUI: PhotographerUI) = false
    fun same(photographerUI: PhotographerUI) = false
}