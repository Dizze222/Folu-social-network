package ch.b.retrofitandcoroutines.all_posts.data

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.all_posts.data.mappers.PhotographerDataToDomainMapper


interface PhotographerData {
    fun <T> map(mapper: PhotographerDataToDomainMapper<T>): T

    data class Base(
        private val id: Int,
        private val author: String,
        private val URL: String,
        private val like: Long,
        private val theme: String,
        private val comments: List<String>,
        private val authorOfComments: List<String>,
        private val favourite: Boolean
    ) : Abstract.DataObject, PhotographerData {
        override fun <T> map(mapper: PhotographerDataToDomainMapper<T>): T =
            mapper.map(id, author, URL, like, theme, comments, authorOfComments, favourite)
    }
}