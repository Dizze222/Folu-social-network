package ch.b.retrofitandcoroutines.presentation.all_posts

import ch.b.retrofitandcoroutines.core.Abstract
import io.realm.RealmList

sealed class PhotographerUI : Abstract.Object<Unit, PhotographerUI.StringMapper>, Comparing {
    override fun map(mapper: StringMapper) = Unit

    object Progress : PhotographerUI()

    object EmptyData : PhotographerUI()

    class Base(
        private val id: Int,
        private val author: String,
        private val URL: String,
        private val like: Long,
        private val theme: String,
        private val comments: RealmList<String>,
        private val authorOfComments: RealmList<String>
    ) : PhotographerUI() {
        override fun map(mapper: StringMapper) =
            mapper.map(id, author, URL, like, theme, comments, authorOfComments)

        override fun same(photographerUI: PhotographerUI) =
            photographerUI is Base && id == photographerUI.id

        override fun sameContent(photographerUI: PhotographerUI) =
            photographerUI is Base && id == photographerUI.id
    }

    class Fail(private val message: String) : PhotographerUI() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }


    interface StringMapper : Abstract.Mapper {
        fun map(
            id: Int,
            author: String,
            URL: String,
            like: Long,
            theme: String,
            comments: RealmList<String>,
            authorOfComments: RealmList<String>
        )

        fun map(message: String)

    }

}

interface Comparing {
    fun sameContent(photographerUI: PhotographerUI) = false
    fun same(photographerUI: PhotographerUI) = false
}