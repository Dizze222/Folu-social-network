package ch.b.retrofitandcoroutines.all_posts.data.mappers

import ch.b.retrofitandcoroutines.utils.core.Abstract

interface PhotographerDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>,
        favourite: Boolean
    ): T
}