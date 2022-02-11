package ch.b.retrofitandcoroutines.presentation

import ch.b.retrofitandcoroutines.core.Abstract

sealed class PhotographerUI : Abstract.Object<Unit, PhotographerUI.StringMapper> {
    override fun map(mapper: StringMapper) = Unit

    object Progress : PhotographerUI()

    class Base(
        private val id: Int,
        private val author: String,
        private val URL: String,
        private val like: Long,
        private val theme: String
    ) : PhotographerUI() {
        override fun map(mapper: StringMapper) = mapper.map(id, author, URL, like, theme)

    }

    class Fail(private val message: String) : PhotographerUI() {
        override fun map(mapper: StringMapper) = mapper.map(message)
    }

    interface StringMapper : Abstract.Mapper {
        fun map(id: Int, author: String, URL: String, like: Long, theme: String)

        fun map(message: String)
    }

}