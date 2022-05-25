package ch.b.retrofitandcoroutines.data.all_posts.mappers

import ch.b.retrofitandcoroutines.core.Abstract
import ch.b.retrofitandcoroutines.domain.all_posts.PhotographerDomain

interface PhotographerDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>
    ): T
}