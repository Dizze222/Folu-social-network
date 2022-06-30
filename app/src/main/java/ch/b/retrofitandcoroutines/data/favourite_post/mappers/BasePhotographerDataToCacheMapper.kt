package ch.b.retrofitandcoroutines.data.favourite_post.mappers

import ch.b.retrofitandcoroutines.data.all_posts.cache.CachePhotographer
import ch.b.retrofitandcoroutines.data.all_posts.mappers.PhotographerDataToDomainMapper

class BasePhotographerDataToCacheMapper : PhotographerDataToDomainMapper<CachePhotographer.Base> {
    override fun map(
        id: Int,
        author: String,
        URL: String,
        like: Long,
        theme: String,
        comments: List<String>,
        authorOfComments: List<String>
    ): CachePhotographer.Base {
        return CachePhotographer.Base(id, author, URL, like, theme, comments, authorOfComments)
    }


}