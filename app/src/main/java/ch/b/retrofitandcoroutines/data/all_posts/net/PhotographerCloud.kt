package ch.b.retrofitandcoroutines.data.all_posts.net

import ch.b.retrofitandcoroutines.core.Abstract

import com.google.gson.annotations.SerializedName


interface PhotographerCloud{
    fun <T> map(mapper: Abstract.ToPhotographerMapper<T>): T

    data class Base(
        @SerializedName("idPhotographer")
        private val id: Int,
        private val author: String,
        @SerializedName("url")
        private val URL: String,
        private val like: Long,
        private val theme: String,
        private val comments: List<String>,
        private val authorOfComments: List<String>
    ) : PhotographerCloud{
        override fun <T> map(mapper: Abstract.ToPhotographerMapper<T>): T {
            return mapper.map(id, author, URL, like, theme, comments, authorOfComments)
        }
    }
}